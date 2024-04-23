<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%
        request.setCharacterEncoding("UTF-8"); //해당시스템의 인코딩타입이 UTF-8일 경우
        //request.setCharacterEncoding("EUC-KR"); //해당시스템의 인코딩타입이 EUC-KR일 경우
        //한글이 깨지는 경우 주석 제거
        String inputYn = request.getParameter("inputYn");
        String roadFullAddr = request.getParameter("roadFullAddr");
        String roadAddrPart1 = request.getParameter("roadAddrPart1");
        String roadAddrPart2 = request.getParameter("roadAddrPart2");
        String engAddr = request.getParameter("engAddr");
        String jibunAddr = request.getParameter("jibunAddr");
        String zipNo = request.getParameter("zipNo");
        String addrDetail = request.getParameter("addrDetail");
        String admCd = request.getParameter("admCd");
        String rnMgtSn = request.getParameter("rnMgtSn");
        String bdMgtSn = request.getParameter("bdMgtSn");
        String detBdNmList = request.getParameter("detBdNmList");
        //** 2017년 2월 추가제공 **/
        String bdNm = request.getParameter("bdNm");
        String bdKdcd = request.getParameter("bdKdcd");
        String siNm = request.getParameter("siNm");
        String sggNm = request.getParameter("sggNm");
        String emdNm = request.getParameter("emdNm");
        String liNm = request.getParameter("liNm");
        String rn = request.getParameter("rn");
        String udrtYn = request.getParameter("udrtYn");
        String buldMnnm = request.getParameter("buldMnnm");
        String buldSlno = request.getParameter("buldSlno");
        String mtYn = request.getParameter("mtYn");
        String lnbrMnnm = request.getParameter("lnbrMnnm");
        String lnbrSlno = request.getParameter("lnbrSlno");
        String emdNo = request.getParameter("emdNo");
    %>
</head>
<script language="javascript">
    // opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다.
    // ("주소입력화면 소스"도 동일하게 적용시켜야 합니다.)
    //document.domain = "abc.go.kr";
    function init(){
        var url = location.href;
        var confmKey = "devU01TX0FVVEgyMDI0MDQwNDE3MDAzMzExNDY2MzM="; // 연계신청시 부여받은 승인키 입력(테스트용 승인키 : TESTJUSOGOKR)
        //var confmKey = "U01TX0FVVEgyMDIyMTIwMjExMjYwNTExMzI4ODM"; // 연계신청시 부여받은 승인키 입력(테스트용 승인키 : TESTJUSOGOKR)
        var resultType = "3";
        var admCd = "<%= admCd %>";
        var rnMgtSn = "<%= rnMgtSn %>";
        var udrtYn = "<%= udrtYn %>";
        var buldMnnm = "<%= buldMnnm %>";
        var buldSlno = "<%= buldSlno %>";

        // 도로명주소 검색결과 화면 출력유형,
        // 1 : 도로명, 2 : 도로명+지번+상세보기(관련지번, 관할주민센터), 3 : 도로명+상세보기(상
        //세건물명), 4 : 도로명+지번+상세보기(관련지번, 관할주민센터, 상세건물명)
        var useDetailAddr = "Y";
        var inputYn= "<%=inputYn%>";
        if(inputYn != "Y"){
            document.form.confmKey.value = confmKey;
            document.form.returnUrl.value = url;
            //document.form.resultType.value = resultType;
            document.forms["form"]["useDetailAddr"].value = useDetailAddr;
            //document.form.action="https://business.juso.go.kr/addrlink/addrLinkUrl.do"; //인터넷망(행정망의 경우 별도 문의)

            //document.form.action="https://business.juso.go.kr/addrlink/addrDetailApi.do"; //인터넷망(행정망의 경우 별도 문의)

            document.form.action="https://business.juso.go.kr/addrlink/addrDetailApi.do?confmKey="+confmKey+"&admCd="+admCd+"&rnMgtSn="+rnMgtSn+"&udrtYn="+udrtYn+"&searchType="+searchType+"&dongNm="+dongNm+"&buldMnnm="+buldMnnm+"&buldSlno="+buldSlno+"&resultType=json";
                                //https://business.juso.go.kr/addrlink/addrDetailApi.do?confmKey=devU01TX0FVVEgyMDI0MDQwNDE3MDAzMzExNDY2MzM=&admCd=1144012700&rnMgtSn=114404000002&udrtYn=0&searchType=floorho&dongNm=&buldMnnm=19&buldSlno=0&resultType=json


                //** 2017년 5월 모바일용 팝업 API 기능 추가제공 **/
            //document.form.action="https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do"; //모바일웹인 경우, 인터넷망
            document.form.submit();
        }else{
            opener.jusoCallBack("<%=roadFullAddr%>","<%=roadAddrPart1%>","<%=addrDetail%>","<%=roadAddrPart2%>",
                "<%=engAddr%>", "<%=jibunAddr%>","<%=zipNo%>","<%=admCd%>","<%=rnMgtSn%>","<%=bdMgtSn%>",
                "<%=detBdNmList%>","<%=bdNm%>","<%=bdKdcd%>", "<%=siNm%>","<%=sggNm%>","<%=emdNm%>",
                "<%=liNm%>","<%=rn%>","<%=udrtYn%>","<%=buldMnnm%>","<%=buldSlno%>","<%=mtYn%>",
                "<%=lnbrMnnm%>","<%=lnbrSlno%>","<%=emdNo%>");
            window.close();
        }
    }
</script>
<body onload="init();">
<form id="form" name="form" method="post">
    <input type="hidden" id="confmKey" name="confmKey" value=""/>
    <input type="hidden" id="returnUrl" name="returnUrl" value=""/>
    <input type="hidden" id="resultType" name="resultType" value=""/>
    <input type="hidden" id="useDetailAddr" name="useDetailAddr" value="Y"/><!-- 요청 변수 추가 (상세주소API 사용여부) -->
</form>
</body>
</body>
</html>