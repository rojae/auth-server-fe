$(window).ready(function() {

    $("input:checkbox").click(function (){
        let boxs = $('.agree-checkbox');

        // 전체동의를 클릭한 경우
        if(this.id === 'agreeAll'){
            // 전체동의
            if($('#agreeAll')[0].checked){
                for(let i=0; i<boxs.length; i++){
                    boxs[i].checked = true;
                }
            }
            // 전체동의 해제
            else{
                for(let i=0; i<boxs.length; i++){
                    boxs[i].checked = false;
                }
            }
        }
        // 전체동의 클릭이 아닌 경우
        else{
            for(let i=0; i<boxs.length; i++){
                // 전체동의가 아닌 버튼 클릭, 전체동의를 비활성화하는 경우
                if(!boxs[i].checked){
                    $('#agreeAll')[0].checked = false;
                    break;
                }
                // 전체동의가 아닌 버튼을 클릭, 전체동의를 활성화하는 경우
                else if(i === boxs.length-1 && boxs[i].checked){
                    $('#agreeAll')[0].checked = true;
                }
            }

        }

    });

    $("#btn-signup-terms").click(function (){
        let boxs = $('.agree-checkbox');
        for(let i=0; i<boxs.length; i++){
            if(!boxs[i].checked){
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `필수 이용약관에 동의해야만 서비스 이용이 가능합니다.`,
                    callback: function () {
                        $("#agreeAll").focus();
                    }
                });
            }
        }

        return bootbox.alert({
            size: "small",
            title: "알림",
            message: `[${new Date().toLocaleString()}]<br/>필수 이용약관에 동의하셨습니다.`,
            callback: function () {
                window.location.replace("/signup/step1");
            }
        });
    });

    // 개인정보 관련 약관 보기
    $("#view-agree-personalInfo").click(function (){
        let loc = '/signup/terms/view/personalInfo';
        window.open(loc, "_blank");
        window.focus();
    });


});
