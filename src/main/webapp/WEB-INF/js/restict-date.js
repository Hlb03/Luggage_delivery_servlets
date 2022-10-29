var today = new Date().toISOString().split('T')[0] + 2;
document.getElementsByName("setTodaysDate")[0].setAttribute('min', today);


// <input name="setTodaysDate" type="date" min="2022-10-25">
//     <script type="text/javascript">//
//         <
//         ![CDATA[
//         var today = new Date().toISOString().split('T')[0];
//         document.getElementsByName("setTodaysDate")[0].setAttribute('min', today
//        )
//         ;
//         ]]></script>
