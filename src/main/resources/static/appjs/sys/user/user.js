let prefix = "/sys/menu"

$(function () {
    console.log(2);
    menuList();
});

/**
 * @description 查询当前登录人拥有菜单
 * @param
 * @return
 * @author liucancan
 * @date 2018/12/5
 */
function menuList() {
    $.get("/sys/menu/menuList.json", function (result) {
        console.log(result);
    });
}