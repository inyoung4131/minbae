// get storeIdx and set href
window.onload = function (){
    const storeIdxVal = window.sessionStorage.getItem('storeIdx');
    const ownerIdxVal = JSON.parse(window.sessionStorage.getItem("memberData"));
    let ownerIdx = ownerIdxVal.memberData.ownerIdx;
    document.getElementById('first-item-href').setAttribute('href', '/owner/selectStore/' +ownerIdx);
    document.getElementById('second-item-href').setAttribute('href', '/owner/storeInfo/' +storeIdxVal);
    document.getElementById('third-item-href').setAttribute('href', '/owner/menu/'+storeIdxVal);
    document.getElementById('fourth-item-href').setAttribute('href', '/owner/menu/create/'+storeIdxVal);
    document.getElementById('fifth-item-href').setAttribute('href', '/owner/store/'+storeIdxVal+'/reviews');
    document.getElementById('sixth-item-href').setAttribute('href', '/owner/store/tradeHistory/infinity/'+storeIdxVal);
};