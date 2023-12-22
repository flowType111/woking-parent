function addPaymentOption(paymentMethod) {
    // 创建支付方式的元素
    var paymentOption = document.createElement('div');
    paymentOption.className = 'paymentOption';
    paymentOption.innerHTML = '<img src="' + paymentMethod.imagePath + '" alt="' + paymentMethod.paymentMethod + '">';

    // 将支付方式添加到容器中
    document.getElementById('paymentOptionsContainer').appendChild(paymentOption);

    // 添加点击事件处理程序
    paymentOption.addEventListener('click', function () {
        handlePaymentClick(paymentMethod);
    });
}

function handlePaymentClick(paymentMethod) {
    // 在这里添加处理支付方式的逻辑
    // 显示支付表单
    document.getElementById('paymentForm').style.display = 'block';

    // 隐藏所有支付方式的表单
    document.querySelectorAll('.inputContainer').forEach(function (container) {
        container.style.display = 'none';
    });

    // 根据选择的支付方式显示相应的表单
    if (paymentMethod.paymentMethod === '4') {
        document.getElementById('blockChannel').style.display = 'block';
    } else if (paymentMethod.paymentMethod === '1') {
        document.getElementById('qrCodeChannel').style.display = 'block';
        // 获取图片和输入框的元素
        var paymentImage = document.getElementById('paymentImage');
        var textInput = document.getElementById('textInput');
        // 监听输入框的输入事件
        textInput.addEventListener('input', function () {
            console.info(paymentMethod.paymentMethod);
            // 发送请求获取图片链接
            fetch(apiUrl + '/common-api/qrcode/getQrCodeImage')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    // 设置图片的 src 属性
                    paymentImage.src = data.data.qrCodePath;
                    var data = {payType: paymentMethod.paymentMethod, qrCodeId: data.data.qrCodeId};
                    savePayment(data);
                })
                .catch(error => console.error('Error fetching payment image:', error));
        });
    }
}
// 获取到二维码请求后台生成订单号
function savePayment(data){
    // 在这里添加提交支付的逻辑
    fetch(apiUrl + '/web-api/pay/channel/submitPayWay', {
        method: 'POST', headers: {
            'Content-Type': 'application/json',
        }, body: JSON.stringify(data),
    })
    .then(response => response.json())
    .then(data => {
        // 处理从后端返回的数据
        console.log('Success:', data);
        alert('Payment submitted successfully!');
    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Error submitting payment. Please try again.');
    });
}

function submitPayment() {
    // 在这里添加提交支付的逻辑
    var paymentMethod = '';  // 用于存储选择的支付方式
    var inputData = '';  // 用于存储输入框的数据
    var data = {};

    // 根据显示的表单来确定支付方式和获取相应的数据
    if (document.getElementById('blockChannel').style.display === 'block') {
        paymentMethod = '4';
        inputData = document.getElementById('blockPassword').value;
        data = {payType: paymentMethod, blockPassword: inputData}
    } else {
        // 获取其他支付方式的数据
    }

    // 使用 Fetch API 发送 POST 请求
    fetch(apiUrl + '/web-api/pay/channel/submitPayWay', {
        method: 'POST', headers: {
            'Content-Type': 'application/json',
        }, body: JSON.stringify(data),
    })
        .then(response => response.json())
        .then(data => {
            // 处理从后端返回的数据
            console.log('Success:', data);
            alert('Payment submitted successfully!');
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('Error submitting payment. Please try again.');
        });
}

// 使用fetch获取接口数据
fetch(apiUrl + '/common-api/pay/config/selectAll')
    .then(response => response.json())
    .then(data => {
        // 初始化页面时添加默认的支付方式
        //addPaymentOption({ paymentMethod: '4', name: '卡密锐换', imagePath: 'alipay_logo.png' });
        // 添加其他支付方式
        data.data.forEach(function (paymentMethodData) {
            console.log(paymentMethodData)
            addPaymentOption(paymentMethodData);
        });
    })
    .catch(error => console.error('Error fetching payment methods:', error));