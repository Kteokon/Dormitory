$(document).ready(function () {
    var page = new Page();
    page.init();
});

function Page() {

}

Page.prototype.init = function () {
    var that = this;
    $("#keywords").multiselect();
    this.bindButtons();
    $.get(root + "reports/all", function (data) {
        that.displayTable(data);
    });
};