/* isNumberKey
    Only allows NUMBERS to be keyed into a text field.
    @environment ALL
    @param evt - The specified EVENT that happens on the element.
    @return True if number, false otherwise.
    */
    function isNumberKey(evt) {
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        // Added to allow decimal, period, or delete
        if (charCode == 110 || charCode == 190 || charCode == 46)
        return true;

        if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;

        return true;
    } // isNumberKey

    // Price slider
    var startValue = 50000;
    var endValue = 500000;
    var minValue = 1000;
    var maxValue = 500000;
    $("#slider-container").slider({
        range: true,
        min: minValue,
        max: maxValue,
        values: [startValue, endValue],
        create: function () {
        $("#amount-from").val(startValue);
        $("#amount-to").val(endValue);
    },
        slide: function (event, ui) {
        $("#amount-from").val(ui.values[0]);
        $("#amount-to").val(ui.values[1]);
        var from = $("#amount-from").val();
        var to = $("#amount-to").val();
        console.log(from + " --- " + to);
    }
    });
