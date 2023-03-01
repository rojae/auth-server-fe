$("#btn-signup-step3").click(function (){

    if($("#nickname").val().length === 0){
        return bootbox.alert({
            size: "small",
            title: "알림",
            message: `닉네임이 입력되지 않았아요`,
            callback: function () {
                $("#nickname").focus();
            }
        });
    }

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

    let nickname = $("#nickname").val();
    let identificationNo = $("#identificationNo1").val() + $("#identificationNo2").val() + $("#identificationNo3").val() + $("#identificationNo4").val() + $("#identificationNo5").val() + $("#identificationNo6").val() + $("#identificationNo7").val();
    let mobileTel = $("#mobileTel1").val() + $("#mobileTel2").val() + $("#mobileTel3").val() + $("#mobileTel4").val() + $("#mobileTel5").val() + $("#mobileTel6").val() + $("#mobileTel7").val() + $("#mobileTel8").val() + $("#mobileTel9").val() + $("#mobileTel10").val() + $("#mobileTel11").val();

    let dataJson = JSON.stringify({ 'nickname': nickname, 'identificationNo': identificationNo, 'mobileTel': mobileTel});

    $.ajax({
        url : "/api/v1/signup/custom-info/personal",
        method: "post",
        beforeSend: function(request) {
            request.setRequestHeader("signup_step_uuid", getCookie('signup_step_uuid'));
        },
        contentType : "application/json; charset=utf-8",
        dataType : "json",
        data : dataJson,
        success : function(response){
            console.log(response);

            if(response.code === 'S0000'){
                window.location.replace("/signup/step4");
            }
            else if(response.code === 'S0001') {
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `잘못된 경로이거나 만료된 세션입니다<br/>다시 진행해주세요`,
                    callback: function () {
                        window.location.replace("/home");
                    }
                });
            }
            else if(response.code === 'S0002') {
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `데이터가 조작이 되었어요<br/>다시 진행해주세요`,
                    callback: function () {
                        window.location.replace("/home");
                    }
                });
            }
            else if(response.code === 'A3001'){
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `이미 사용하고 있는 닉네임이예요<br/>다른 닉네임을 사용해주세요`
                });
            }
            else{
                exceptionRedirect();
            }
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
