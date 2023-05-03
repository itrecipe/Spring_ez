/**
 * kook 2023/05/03
 */

console.log("Reply Module...");

//변수 replyService 즉시 실행 함수이자 객체 변수이다.
//즉시 실행 함수는 ()안에 function을 정의하며 실행 연산자()를 끝에 첨부하면 되고 시작시 1회만 실행된다.
let replyService = (function(){
	//중첩된 내부 함수
	//js는 사용 및 호출시 꼭 파라미터의 갯수를 적용하지 않아도 된다.(필요 없으면 안 보내도 됨)
	//callback 함수는 ajax 성공시 실행할 함수, error 함수는 실패시 실행할 내용
	
	function add(reply, callback, error) {
		console.log("add reply...");
		
		//.ajax() 메서드는 서버와 비동기 방식으로 통신을 할 때 사용한다.
		//파라미터로 {}로 JS객체 형식으로 속성들을 작성한다.
		$.ajax({
			type : 'post', //전송방식
			url : '../replies/new', //요청경로
			data : JSON.stringify(reply), //서버로 전송하는 데이터 값(데이터 속성에 값을 넣는다)
			//JSON.stringify(reply) : reply는 JS의 객체형이며, JSON 문자열로 변환하여 보내준다.
			 
			contentType : "application/json; charset=UTF-8", //서버로 보내는 데이터 타입
			//dataType은 서버로 부터 받는 데이터 형식(생략해도 된다.) dataType : "json" <- 이런 형태로 작성됨
			success : function(result, status, xhr) { 
			//전송 성공시 실행할 함수, result는 서버로 부터 받는 값, status는 상태를 의미, xhr은 xmlhttprequest를 의미 한다.
				if(callback) {
				//callback을 인자로 받았으면 true
				//javascript는 꼭 true/false가 아닌 null, 0, undefined 등은 false, 아니면 true로 취급
					callback(result);
				}
			},
			error : function(xhr, status, er) { //실패시 실행될 함수
				
				if(error) {
					error(er);
				}
			}
		});
	}
	
	function getList(param, callback, error) {
 
    var bno = param.bno;
    var page = param.page || 1; //1은 default값으로 값이 없을시 1로 설정한다.
 
    $.getJSON("../replies/pages/" + bno + "/" + page + ".json", //요청경로
        function(data) { //data는 성공시 얻는 데이터(JSON(문자열)), callback함수
          if (callback) {
            callback(data);
          }
        })
        .fail(function(xhr, status, err) {
      if (error) {
        error();
      }
    });
  }
  
	function remove(rno, callback, error) {
		$.ajax({
			type : 'delete', //REST방식
			url : '../replies/' + rno,
			success : function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if(error) {
					error(er);
  				}
  			}
  		});
  	}
  	
  	function update(reply, callback, error) {
  	
  		console.log("RNO : " + reply.rno);
  		
  		$.ajax({
			type : 'put', //update는 PUT 또는 PATCH
			url : '../replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(deleteResult, status, xhr) {
				if(callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if(error) {
					error(er);
  				}
  			}
  		});
  	}
	
	function get(rno, callback, error) {
 
    $.get("../replies/" + rno + ".json", function(result) {
 
      if (callback) {
        callback(result);
      }
 
    }).fail(function(xhr, status, err) {
      if (error) {
        error();
      }
    });
  }
	
	return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get
	};
})();