$.validator.setDefaults(
    {
        submitHandler: function (form) {
            bootbox.confirm("确认提交吗？", function (result) {
                if (result) {
                    form.submit();
                }
            });
        },
        showErrors: function (map, list) {

            //   this.currentElements.parents('.md-input-wrapper:first').parents('parsley-row:first').find('.parsley-errors-list').remove();
            //        this.currentElements.parents('.md-input-wrapper:first').removeClass('md-input-wrapper-danger');
            var labp = this.currentElements.parents('parsley-row:first').length ? this.currentElements.parents('parsley-row:first') : this.currentElements.parents('.parsley-row:first');
            labp.find('.parsley-errors-list').remove();
            $.each(list, function (index, error) {
                var ee = $(error.element);
                var eep = ee.parents('parsley-row:first').length ? ee.parents('parsley-row:first') : ee.parents('.parsley-row:first');

                ee.parents('.md-input-wrapper:first').addClass('md-input-wrapper-danger');
                eep.find('.parsley-errors-list').remove();
                eep.append('<div class="parsley-errors-list filled"><span class="parsley-required"> ' + error.message + '</span></div>');
            });
            //refreshScrollers();
        }
    });

$(document).ready(function () {
    jQuery.extend(jQuery.validator.messages, {
        required: "必填字段",
        remote: "请修正该字段",
        email: "请输入正确格式的电子邮件",
        url: "请输入合法的网址",
        date: "请输入合法的日期",
        dateISO: "请输入合法的日期 (ISO).",
        number: "请输入合法的数字",
        digits: "只能输入正整数",
        creditcard: "请输入合法的信用卡号",
        equalTo: "请再次输入相同的值",
        accept: "请输入拥有合法后缀名的字符串",
        maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
        minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
        rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
        range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
        max: jQuery.validator.format("请输入一个最大为{0} 的值"),
        min: jQuery.validator.format("请输入一个最小为{0} 的值")
    });
});

