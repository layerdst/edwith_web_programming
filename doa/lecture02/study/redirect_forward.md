## Redirect

서버가 클라이언트에게 URL로 이동하라고 요청을 보내는 것, 클라이언트에게 이동할 페이지로 다시 요청을 하도록 함.

- 리다이렉트는 HTTP 프로토콜로 정해진 규칙, 클라이언트에게 특정 URL로 이동하라고 요청
- 서블릿이나 JSP는 리다이렉트하기 위해 HttpServletResponse 클래스의 sendRedirect() 메서드를 사용
- status 302 code, location header : 리다이렉트 페이지, 리다이렉트 페이지 각각 요청(리다이렉트 요청하는 페이지, 리다이렉트 요청 후 페이지는 서로 다른 객체)

**응답과정**
- 이미지 첨부


## Redirect example

redirect01.jsp → redirect02.jsp redirecting

```jsx
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>redirect01</title>
</head>
<body>
<%
	/*redirect02.jsp로 리다이렉트*/
	response.sendRedirect("redirect02.jsp");
%>
</body>
</html>
```

## status 301과 302 코드

Redirection 클래스에 속하는 상태코드들은 클라이언트를 지정된 위치로 이동하시키거나 참조하게 하는 등의 동작

## 301 Redirect (새로운 도메인)

영구이동, 해당 URL이 영구적으로 새로운 URL로 변경되었음을 나타낸다.

301은 도메인을 변경했거나 새로운 URL 구조로 개편했을 때 사용한다.

## 302 Redirect (기존 도메인)

임시적, 요청한 리소스가 임시적으로 새로운 URL로 이동했음을 나타낸다. 컨텐츠만 새로운 URL에서 조회하도록 해야할 때 유용

쇼핑몰과 같은 전자상거래 사이트를 운영할 때 일시적으로 재고가 떨어지거나 특정한 계절이나 기간에만 한정적으로 파는 제품이 있을 경우


## Forward

클라이언트가 서버로 요청, Servlet1이 클라이언트가 받은 요청을 다 처리하지 않고 일정 부분만 처리한 후 Servlet2 가 처리하도록 넘겨주고 Servlet2가 처리한 후 클라이언트에게 응답

- Servlet1은 요청을 처리한 후, 결과를 HttpServletRequest에 저장
- Servlet1은 결과가 저장된 HttpServletRequest와 응답을 위한 HttpServletResponse를 같은 웹 어플리케이션 안에 있는 Servlet2에게 전송(forward)
- Servlet2는 Servlet1으로부터 받은 HttpServletRequest와 HttpServletResponse를 이용하여 요청을 처리

**응답과정**
- 이미지 첨부

## Forward example

**[FrontServlet.java](http://frontservlet.java) (URL mapping) : front**

```jsx
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int diceValue = (int)(Math.random() * 6) + 1;
		request.setAttribute("dice", diceValue);
		// request 에게 diceValue 의 값을 맡겨야 한다.
		
		// 인자로는 어디로 이동할지 넣어준다.
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/next");
		
		// request, response 객체도 함께 넘겨준다.
		requestDispatcher.forward(request, response);	
	}
```

**[NextServlet.java](http://nextservlet.java) (URL mapping : next)**

```jsx
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>from</title></head>");
		out.println("<body>");
		
		/* 값을 찾아와야 함.
		int diceValue = (int)(Math.random() * 6) + 1;
		request.setAttribute("dice", diceValue);
		 - Object -> Integer로 형변환. */
		int dice = (Integer)request.getAttribute("dice");
		out.println("dice : " + dice);
		for (int i = 0; i < dice; i++) {
			out.println("<br>hello");
		}
		
		out.println("</body>");
		out.println("</html>");
	}
```

실행결과 URI은 /front 로 확인된다.

## Redirect 와 Forward 차이 비교

**JSP 환경에서 다른 페이지로 이동하는 두가지 방식**

아래는 redirect와 forward의 차이점을 찾아보다가 발견한 내용. 일상생활의 친근한 개체로 비유하여 설명한 내용이다.

**1) redirect**
1. 고객이 고객센터로 상담원에게 123번으로 전화를 건다.
2. 상담원은 고객에게 다음과 같이 이야기한다. "고객님 해당 문의사항은 124번으로 다시 문의주겠어요?"
3. 고객은 다시 124번으로 문의해서 일을 처리한다.

**2) forward**
1. 고객이 고객센터로 상담원에게 123번으로 전화를 건다.
2. 상담원은 해당 문의사항에 대해 잘 알지 못해서 옆의 다른 상담원에게 해당 문의사항에 답을 얻는다.
3. 상담원은 고객에게 문의사항을 처리해준다.

![redirect와 forward]([https://doublesprogramming.tistory.com/63](https://doublesprogramming.tistory.com/63))

또한 redirect는 URI 가 변하고 forward는 변하지 않는 것을 확인할 수 있음.

## Redirect 와 Forward 적용 사례
*Forward*
웹 브라우저에는 최초에 호출한 URL이 표시되고, 이동한 페이지의 URL 정보는 확인이 어렵다.
현재 실행중인 페이지와 forward에 의해 호출될 페이지를 Request 객체와 Response 객체를 공유하는 Forward 방식은 어떤 때에 사용이 될까?

 - 게시판을 작성하는 과정에서 사용자가 보낸 요청 정보를 이용하여 글쓰기 기능을 수행하여 forward를 사용하여 응답 페이지를 부르면 다음과 같은 문제가 발생할 수 있다.
  - 클라이언트가 실수 혹은 고의로 글쓰기 응답 페이지에서 새로고침을 눌러버릴 경우, 요청 정보가 그대로 살아있어 요청이 여러번 전달되어 동일한 게시물이 여러번 등록이 될 수 있다.
  - 하여 게시판을 제작하는 과정에서는 시스템에 변화가 생기지 않는 **단순 조회 요청(글 목록 보기, 검색)** 의 경우에 forward를 사용한다.
  
*Redirect*
URL을 지시된 주소로 바꾸고 해당 주소로 이동한다.
다른 웹 컨테이너에 있는 주소로 이동하며 새로운 페이지에서는 Request와 Response 객체가 새롭게 생성되는 Redirect 방식은 어떤 때에 사용이 될까?

 - 게시판을 작성하는 과정에서 사용자가 보낸 요청 정보를 이용하여 글쓰기 기능을 수행하여 redirect를 사용하여 응답 페이지를 부르면 다음과 같이 사용될 수 있다.
  - 사용자가 실수 혹은 고의로 글쓰기 응답 페이지에서 새로고침을 누른다고 하더라도, 처음의 요청 정보는 가지고 있지 않으므로 게시판이 여러번 등록되지 않는다.
  - 하여 **시스템에 변화가 생기는 요청(회원가입, 글쓰기 등)** 의 경우에는 redirection 을 사용한다.
