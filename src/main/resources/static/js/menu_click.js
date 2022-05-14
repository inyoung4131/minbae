
const a_back = () => {
    location.href = document.referrer;
}

let totalQuantity = 1;
let fix_Price = document.getElementById("fix_price").innerText;
let totalPrice = parseInt(fix_Price);
function count(type)  {
    // 결과를 표시할 element
    const resultElement = document.getElementById('quantity');
    const s_final_price = document.getElementById('final_menu_price');
    const s_fix_price = document.getElementById('fix_price');

    // 현재 화면에 표시된 값
    totalQuantity = resultElement.innerText;
    totalPrice = parseInt(s_final_price.innerText);
    let price = parseInt(s_fix_price.innerText);

    // 더하기/빼기
    if(type === 'plus') {
        // if (totalQuantity >= 10){
        //     return;
        // }
        totalQuantity = parseInt(totalQuantity) + 1;
        totalPrice = totalPrice + price;

    }else if(type === 'minus')  {
        if (totalQuantity <= 1){
            return;
        }
        totalQuantity = parseInt(totalQuantity) - 1;
        totalPrice = totalPrice - price;
    }
    // 결과 출력
    resultElement.innerText = totalQuantity;
    s_final_price.innerText = `${totalPrice}원`;
    document.getElementById('bt_order').innerText = totalQuantity + '개 담기';
}

//장바구니 담기
window.addEventListener('DOMContentLoaded', () => {
document.getElementById('bt_order').addEventListener('click', () => {

    if(JSON.parse(sessionStorage.getItem('memberData')) != null){
        const store_idx = document.getElementById('store_idx').value;
        const menu_idx = document.getElementById('menu_idx').value;
        const store_name = document.getElementById("store_name").value;
        const menu_name = document.getElementById("menu_name").innerText;
        const menu_price = document.getElementById("fix_price").innerText;
        const deliver_price = document.getElementById("deliver_price").value;
        const menu_img = document.getElementById("menu_img").value;
        const categoryKo = document.getElementById("categoryKo").value;

        //세션스토리지에 저장(하기 전에 null 체크 먼저 해서 null이면 밑에 거, 아니면 조회 후 push로 넣기(장바구니 또 넣기)
        let shoppingList = JSON.parse(sessionStorage.getItem('item'));

        if(shoppingList == null){
            sessionStorage.setItem("item", JSON.stringify([
                [store_idx, menu_idx, store_name, menu_name, menu_price, deliver_price, totalQuantity, totalPrice, menu_img, categoryKo]
            ]));

            alert('장바구니에 메뉴를 추가했습니다.');
            location.href = document.referrer;
        }else {

            //같은 메뉴 선택 시
            let equal_menu_idx = 0;
            let equal_total_quantity = 0;
            let equal_total_price = 0;
            let count = 0;
            for(count; count < shoppingList.length; count++){
                let item = shoppingList[count];
                if(menu_idx == item[1]){
                    //장바구니 안에 기존에 들어 있던 현재 추가 하려는 메뉴와 같은 메뉴의 수량, 가격, idx
                    equal_menu_idx = item[1];
                    equal_total_quantity = item[6];
                    equal_total_price = item[7];
                    break;
                }
            }
            if(menu_idx == equal_menu_idx){
                //기존 수량과 새로 추가하려는 수량, 가격 합침
                totalQuantity = totalQuantity + equal_total_quantity;
                totalPrice = totalPrice + equal_total_price;
                //기존에 있던 같은 메뉴는 삭제
                shoppingList.splice(count, 1);
                //새로 추가
                shoppingList.push([store_idx, menu_idx, store_name, menu_name, menu_price, deliver_price, totalQuantity, totalPrice, menu_img, categoryKo]);
                sessionStorage.setItem('item', JSON.stringify(shoppingList));

                alert('장바구니에 메뉴를 추가했습니다.');
                location.href = document.referrer;
                return;
            }

            //다른 가게의 메뉴 선택 시
            let other_store_idx = [];   //다른 가게 idx의 인덱스 번호들 담을 배열
            let other_count = 0;
            for(other_count; other_count < shoppingList.length; other_count++){
                let item = shoppingList[other_count];
                if(store_idx != item[0]){
                    other_store_idx.push(other_count);
                }
            }

            if(store_idx != shoppingList[0][0]){
                let confirm_result = confirm("장바구니에는 같은 가게의 메뉴만 담을 수 있습니다. \n" +
                    "선택하신 메뉴를 장바구니에 담을 경우 이전에 담은 메뉴가 삭제됩니다.");

                if(confirm_result){
                    for(let i = 0; i < other_store_idx.length; i++){
                        shoppingList.splice(i, other_count);
                    }
                }else{
                    return;
                }
                shoppingList.push([store_idx, menu_idx, store_name, menu_name, menu_price, deliver_price, totalQuantity, totalPrice, menu_img, categoryKo]);
                sessionStorage.setItem('item', JSON.stringify(shoppingList))

                alert('장바구니에 메뉴를 추가했습니다.');
                location.href = document.referrer;
                return;
            }

            //아무 조건에 걸리는 거 없다면 이게 저장
            shoppingList.push([store_idx, menu_idx, store_name, menu_name, menu_price, deliver_price, totalQuantity, totalPrice, menu_img, categoryKo]);
            //shoppingList.push를 하면 원래 있던 거에 추가, 이후에 setItem에 같은 key에 shoppingList를 추가하면 덮어씌워지게 됨
            sessionStorage.setItem('item', JSON.stringify(shoppingList));

            alert('장바구니에 메뉴를 추가했습니다.');
            location.href = document.referrer;

        }
    }else{
        alert('로그인 후 이용 가능합니다.')
    }

})
})
