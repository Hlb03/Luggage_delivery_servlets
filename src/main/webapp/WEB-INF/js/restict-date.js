
function setMinDate() {
    var today = new Date().toISOString().split('T')[0];
    document.getElementsByName("luggage-del-date")[0].setAttribute('min', '2022-28-10');
    alert(today)
}


// <input name="setTodaysDate" type="date" min="2022-10-25">
//     <script type="text/javascript">//
//         <
//         ![CDATA[
//         var today = new Date().toISOString().split('T')[0];
//         document.getElementsByName("setTodaysDate")[0].setAttribute('min', today
//        )
//         ;
//         ]]></script>
