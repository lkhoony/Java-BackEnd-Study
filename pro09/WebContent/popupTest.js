window.onload = pageLoad;

function pageLoad(){
  notShowPop = getCookieValue();
  if(notShowPop!="true"){
    window.open("popUp.html","pop","width=400, height=500, history=no, resizable=no, status=no, scrollbars=yes, menubar=no");
    // https://lktprogrammer.tistory.com/127 >> 팝업창 띄우기
  }

}

function getCookieValue(){
  var result = "false";

  if(document.cookie !=""){
    var cookie = document.cookie.split(";");
    for(var i=0;i<cookie.length;i++){
      var element = cookie[i].split("=");
      var value = element[0];
      value = value.replace(/^\s*/,'');
      if(value==="notShowPop"){
        result=element[1];
        console.log(result);
      }
    }
  }

  return result;
}

function deleteCookie(){
  document.cookie = "notShowPop="+"false"+";path=/; expires=-1";
}
