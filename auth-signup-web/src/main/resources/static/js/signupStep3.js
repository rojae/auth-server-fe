$("#btn-signup-step3").click(function (){

    if($("#identificationNo1").val().length === 0 || $("#identificationNo2").val().length === 0
        || $("#identificationNo3").val().length === 0 || $("#identificationNo4").val().length === 0
        || $("#identificationNo5").val().length === 0 || $("#identificationNo6").val().length === 0
        || $("#identificationNo7").val().length === 0){

        return bootbox.alert({
            size: "small",
            title: "알림",
            message: `주민등록번호가 입력되지 않았어요`,
            callback: function () {
                $("#identificationNo1").focus();
            }
        });
    }


    if($("#mobileTel1").val().length === 0 || $("#mobileTel2").val().length === 0
        || $("#mobileTel3").val().length === 0 || $("#mobileTel4").val().length === 0
        || $("#mobileTel5").val().length === 0 || $("#mobileTel6").val().length === 0
        || $("#mobileTel7").val().length === 0 || $("#mobileTel8").val().length === 0
        || $("#mobileTel9").val().length === 0 || $("#mobileTel10").val().length === 0
        || $("#mobileTel11").val().length === 0) {

        return bootbox.alert({
            size: "small",
            title: "알림",
            message: `휴대폰 번호가 입력되지 않았어요`,
            callback: function () {
                $("#mobileTel1").focus();
            }
        });
    }

    return bootbox.alert({
        size: "middle",
        title: "알림",
        message: `아직 개발 중인 페이지이예요 😅<br/>로그인 페이지로 이동할게요`,
        callback: function () {
            let loc = document.querySelector("#web-signin").getAttribute("data-contextPath");
            window.location.replace(loc);
        }
    });
});


$(document).ready(function(){
    $("#nickName").focus();

    /////// identificationNo /////////
    $("#identificationNo1").on("keyup",function(){
        if(this.value.length === 1){
            $("#identificationNo2").focus();
        }
    });
    $("#identificationNo2").on("keyup",function(){
        if(this.value.length === 1){
            $("#identificationNo3").focus();
        }
    });
    $("#identificationNo3").on("keyup",function(){
        if(this.value.length === 1){
            $("#identificationNo4").focus();
        }
    });
    $("#identificationNo4").on("keyup",function(){
        if(this.value.length === 1){
            $("#identificationNo5").focus();
        }
    });
    $("#identificationNo5").on("keyup",function(){
        if(this.value.length === 1){
            $("#identificationNo6").focus();
        }
    });
    $("#identificationNo6").on("keyup",function(){
        if(this.value.length === 1){
            $("#identificationNo7").focus();
        }
    });
    $("#identificationNo7").on("keyup",function(){
        if(this.value.length === 1){
            console.log('ok');
        }
    });


    //////////// mobileTel ///////////
    $("#mobileTel1").on("keyup",function(){
        if(this.value.length === 1){
            $("#mobileTel2").focus();
        }
    });
    $("#mobileTel2").on("keyup",function(){
        if(this.value.length === 1){
            $("#mobileTel3").focus();
        }
    });
    $("#mobileTel3").on("keyup",function(){
        if(this.value.length === 1){
            $("#mobileTel4").focus();
        }
    });
    $("#mobileTel4").on("keyup",function(){
        if(this.value.length === 1){
            $("#mobileTel5").focus();
        }
    });
    $("#mobileTel5").on("keyup",function(){
        if(this.value.length === 1){
            $("#mobileTel6").focus();
        }
    });
    $("#mobileTel6").on("keyup",function(){
        if(this.value.length === 1){
            $("#mobileTel7").focus();
        }
    });
    $("#mobileTel7").on("keyup",function(){
        if(this.value.length === 1){
            $("#mobileTel8").focus();
        }
    });
    $("#mobileTel8").on("keyup",function(){
        if(this.value.length === 1){
            $("#mobileTel9").focus();
        }
    });
    $("#mobileTel9").on("keyup",function(){
        if(this.value.length === 1){
            $("#mobileTel10").focus();
        }
    });
    $("#mobileTel10").on("keyup",function(){
        if(this.value.length === 1){
            $("#mobileTel11").focus();
        }
    });
    $("#mobileTel11").on("keyup",function(){
        if(this.value.length === 1){
            console.log('ok');
        }
    });

});




function exceptionRedirect() {
    bootbox.alert({
        size: "middle",
        title: "알림",
        message: `죄송합니다. 일시적인 오류로 인해서 처리하지 못했습니다.<br>잠시 뒤에 시도해주세요.`,
        callback: function () {
            let loc = document.querySelector("#web-signin").getAttribute("data-contextPath");
            window.location.replace(loc);
        }
    });
}

function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
        object.value = object.value.slice(0, object.maxLength);
    }
}
