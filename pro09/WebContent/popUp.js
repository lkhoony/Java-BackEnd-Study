function setPopUpStart(obj){
  if(obj.checked===true){
    var expireDate = new Date();
    expireDate.setMonth(expireDate.getMonth()+1);
    document.cookie="notShowPop=true"+";path=/; expires=" + expireDate.toGMTString();
    window.close();
  }
}
