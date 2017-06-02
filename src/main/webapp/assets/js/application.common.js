$(document).ready(function () {
    kendo.culture("zh-CN");
});

/**
 * 格式化枚举国际化
 * @param enumName 枚举名称
 * @param enumValue 枚举值
 * @returns {*}
 */
function formatEnumI18n(enumName, enumValue) {
    var varName = enumName + "_" + enumValue;
    return i18n_enum[varName];
}


/**
 * 获取表格选中行
 * @param gridName
 * @returns {*}
 */
function getSelectedGridItem(gridName) {
    var grid = $("#" + gridName).data("kendoGrid");
    var selectedRows = grid.select();
    if (selectedRows.length > 0) {
        return grid.dataItem(selectedRows[0]);
    }
    UIkit.notify({message: "请从表格中选择操作的数据", status: 'warning', timeout: 3000, pos: 'top-center'});
    return null;
}

