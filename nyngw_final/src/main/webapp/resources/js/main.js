
$(function() {
    $( ".inputTypeDate" ).datepicker({ dateFormat: 'yy-mm-dd'}); 
    $( ".inputTypeDate2" ).datepicker({ dateFormat: 'yy/mm/dd'}); 
	$( ".inputTypeMonth" ).MonthPicker({ 
		pattern:'yy/mm',
		dateFormat: 'yy/mm',
		yearSuffix: '',     // Additional text to append to the year in the month headers 
		prevText: 'Prev',   // Display text for previous month link 
		nextText: 'Next',   // Display text for next month link 
		monthNames: ['1월','2월','3월','4월','5월','6월', '7월','8월','9월','10월', '11월','12월'],  
	}); 

})

function printClock() {
	    
    var clock = document.getElementById("clock");// 출력할 장소 선택
    var currentDate = new Date();// 현재시간
    var calendar = currentDate.getFullYear() 
    				+ "-" + (currentDate.getMonth()+1) 
    				+ "-" + currentDate.getDate() // 현재 날짜
    var amPm = 'AM'; // 초기값 AM
    var currentHours = addZeros(currentDate.getHours(),2); 
    var currentMinute = addZeros(currentDate.getMinutes() ,2);
    var currentSeconds =  addZeros(currentDate.getSeconds(),2);
    
    if(currentHours >= 12){ // 시간이 12보다 클 때 PM으로 세팅, 12를 빼줌
    	amPm = 'PM';
    	currentHours = addZeros(currentHours - 12,2);
    }

    if(currentSeconds >= 50){// 50초 이상일 때 색을 변환해 준다.
       currentSeconds = '<span style="color:#de1951;">'+currentSeconds+'</span>'
    }
    clock.innerHTML = currentHours+":"+currentMinute+":"+currentSeconds +" <span style='font-size:24px;'>"+ amPm+"</span>"; //날짜를 출력해 줌
    
    setTimeout("printClock()",1000);         // 1초마다 printClock() 함수 호출
}


function addZeros(num, digit) { // 자릿수 맞춰주기
	var zero = '';
	num = num.toString();
	if (num.length < digit) {
		for (i = 0; i < digit - num.length; i++) {
			zero += '0';
		}
	}
	return zero + num;
}



