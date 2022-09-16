
function calculate() {

    let inputValue = Number(document.getElementById("numbers-input").value)
    let outputValue = Number(document.getElementById("numbers-output").value)

    let currencyInput = Number(document.getElementById("currency-input").value)
    let currencyOutput = Number(document.getElementById("currency-output").value)

    if (!(Number.isNaN(currencyInput) || Number.isNaN(currencyOutput))) {
        if (inputValue > 0) {
            outputValue = (inputValue * currencyInput) / currencyOutput
            document.getElementById("numbers-output").placeholder = "You receive: ~" + Number(outputValue).toFixed(4)
        } else {
            document.getElementById("numbers-output").placeholder = ""
        }
    } else {
        document.getElementById("numbers-output").placeholder = ""
    }
}