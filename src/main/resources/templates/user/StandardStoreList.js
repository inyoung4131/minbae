var page=0;
var user_lat=JSON.parse(window.localStorage.getItem("user_latlng")).user_lat;
var user_lng=JSON.parse(window.localStorage.getItem("user_latlng")).user_lng;
var resultdata;

$('#body').on('load',requestStandardStoreList());

window.onscroll = function(e) {
    if((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
        requestStandardStoreList();
        page++;
    }
}

function requestStandardStoreList(){
    $.ajax({
        type: 'GET',
        url: '/user/category/standard?paging='+page+'&user_lat='+user_lat+'&user_lng='+user_lng+'&category='+category,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
    }).done(function (data) {
        if(data!=null&&data.length>0){
            for(var i=0;i<data.length;i++){
                var form = document.createElement("form");
                var div = document.createElement("div");
                var div1 = document.createElement("div");
                var div2 = document.createElement("div");
                var div3 = document.createElement("div");
                var div4 = document.createElement("div");
                div.style.marginTop="40px";
                div.style.border="1px solid black";
                div1.innerText=data[i].store_detail_info_img;
                div2.innerText=data[i].store_name;
                div3.innerText="평점:"+ data[i].avger_star + "리뷰 수 :" + data[i].cou;
                div4.innerText="최소 배달금액"+data[i].store_detail_minimum_price;
                div.append(div1);
                div.append(div2);
                div.append(div3);
                div.append(div4);
                form.append(div);
                $('#storeListDiv').append(form);
            }
        }
        resultdata=data;
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}