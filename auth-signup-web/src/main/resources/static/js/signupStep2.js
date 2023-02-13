$("#btn-signup-pre-step2").click(function (){
    bootbox.alert({
        size: "middle",
        title: "알림",
        message: `아직 개발 중인 페이지이예요 😅<br/>로그인 페이지로 이동할게요`,
        callback: function () {
            let loc = document.querySelector("#web-signin").getAttribute("data-contextPath");
            window.location.replace(loc);
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

