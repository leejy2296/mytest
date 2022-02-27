
var array = [];
var i = 1;

function addQuestion(){
    var c = "count"+(i-1);
    var addDiv = document.createElement("div");
    addDiv.setAttribute("id","addQuestion"+i);
    addDiv.setAttribute("class","card-details");
    addDiv.innerHTML = 
        "<p id='count"+i+"'>"+(parseInt($('#'+c).text())+1)+"</p>"+
        "<label for='questionContent'>문제 내용</label>"+
        "<input class='form-control' type='text' id='questionContent"+i+"' name='questionContent' placeholder='문제를 입력해 주세요.'/>"+
        "<label for='score'>배점</label>"+
        "<input class='form-control' type='number' id='score"+i+"' name='score'>"+
        "<label for='answer'>문제의 답</label>"+
        "<input class='form-control' type='text' id='answer"+i+"' name='answer' placeholder='답을 입력해 주세요.'/>";
            
    questionForm.appendChild(addDiv);
            
    i++;
    }
    
        
function insertQuestion(){
    for(var e=0;e<=i-1;e++){
                
        var list = {
            testlistId : "testlistId",
            classId : "classId",
            academyId : "academyId",
            questionContent : "questionContent",
            score : "score",
            answer : "answer"
        };
        // 문제에 빈칸있는지 검사
        if(document.getElementById("TestlistId").value !== "" &&
            document.getElementById("classId").value !== "" &&
            document.getElementById("academyId").value !== "" &&
            document.getElementById("questionContent"+e).value !== "" &&
            document.getElementById("score"+e).value !== "" &&
            document.getElementById("answer"+e).value !== "")
            {
                list.testlistId = document.getElementById("TestlistId").value;
                list.classId = document.getElementById("classId").value;
                list.academyId = document.getElementById("academyId").value;
                list.questionContent = document.getElementById("questionContent"+e).value;
                list.score = document.getElementById("score"+e).value;
                list.answer = document.getElementById("answer"+e).value;
                    
                array.push(list);
            }else {
                alert('빈칸을 입력해주세요.');
                return false;
            }
    }
            
    $.ajax({
        url:'/test/questionRegister',
        type:'post',
        contentType:'application/json',
        data: JSON.stringify(array),
            success:function(data){
                console.log(data);
                if(data==1){
                    alert('문제 등록 완료');
                    location.href= "main.html";
                }else{
                        
                }
            },
            error:function(){
            alert('에러입니다.');
            }
        });
            
}