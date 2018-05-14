<%@page import="demo.spec.Message"%>
<%@ page import="demo.spec.UserAccess"%>
<%@ page import="java.util.List" %>

<head>
    <meta http-equiv="Expires" CONTENT="0">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Message Wall</title>
</head>

<%
    UserAccess userAccess = (UserAccess) session.getAttribute("useraccess");
    List<Message> messages = userAccess.getAllMessages();
    int number = userAccess.getNumber();
%>

<script>
    function pollingFunction() {
        var x = document.getElementById('refresh').submit();
        console.log("refreshing...")
    }
    var t = setInterval(pollingFunction,5000);
</script>

<body>

    <h3>user: <em><%=userAccess.getUser()%></em>
        <a href=logout.do>[Close session]</a></h3>

    <h2> <%=number%> Messages shown:</h2>

    <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">

        <td width="14%" valign="center" align="middle">
            Message
        </td>

        <td width="14%" valign="center" align="middle">
            Owner
        </td>

        <td width="14%" valign="center" align="middle">
            Click to:
        </td>

        <%
            int index = -1;
            for (Message message : messages) {
                index += 1;
        %>

        <tr> <font size="2" face="Verdana">

        <td width="14%" valign="center" align="middle">
            <%=message.getContent()%>
        </td>

        <td width="14%" valign="center" align="middle">
            <%=message.getOwner()%>
        </td>

        <td width="14%" valign="center" align="middle">
            <form action="delete.do" method="post">
                <input type="hidden"
                       name="index"
                       value="<%=index%>">
                <input type="submit"
                       name="delete"
                       value="delete">
            </form>
        </td>

        </font> 
    </tr>

    <% } %>

</table>

</br>

<HR WIDTH="100%" SIZE="2">

<form action="put.do" method=POST>
    New message:<input type=text name=msg size=10>
    <input type=submit value="Send message">
</form>

<HR WIDTH="100%" SIZE="2">

<form action="refresh.do" method=POST id="refresh">
    <input type=submit value="Refresh wall view message">
</form>

</body>