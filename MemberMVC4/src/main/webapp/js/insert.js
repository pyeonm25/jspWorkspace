let isIdValid = 0;
const form = document.querySelector('form');
const inputs = form.querySelectorAll('input');
const idInput = document.querySelector('#id');
const checkIdButton = document.querySelector('#checkId');


form.addEventListener('submit', (event) => {
    event.preventDefault();
    if (isIdValid !== 1) {
        alert('먼저 아이디 중복 확인을 해주세요');
        idInput.focus();
        return;
    }
    if (validateAll()) {
        form.submit();
    }
});


inputs.forEach(input => {
    input.addEventListener('input', () => validateField(input));
});


checkIdButton.addEventListener('click', async () => {
    const id = idInput.value.trim();

    if (!id) {
        alert('ID를 입력해주세요');
        idInput.focus();
        idInput.style.border = '2px solid red';
        return;
    }

    try {
        const response = await fetch('vaildIdAjax.do', {          // request 객체 
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            },
            body: new URLSearchParams({ id  }).toString(),  // {"id" : id}   => request.getParameter 
        });

        if (!response.ok) {  // response.ok == 200 
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.text();
        handleIdValidationResult(result);
    } catch (error) {
        console.error('ID validation error:', error);
        alert('아이디 확인 중 오류가 발생했습니다');
        idInput.style.border = '2px solid orange';
    }
});


idInput.addEventListener('input', () => {
    if (isIdValid !== 0) {
        isIdValid = 0;
        idInput.removeAttribute('readonly');
    }
});

function validateField(input) {
    const value = input.value.trim();
    let isValid = true;
    let message = '';

    switch (input.id) {
        case 'id':
            if (!value) {
                message = '아이디를 입력해주세요';
                isValid = false;
            }
            break;

        case 'pass':
            if (!value) {
                message = '패스워드를 입력해주세요';
                isValid = false;
            }
            break;

        case 'name':
            if (!value) {
                message = '이름을 입력해주세요';
                isValid = false;
            }
            break;

        case 'age':
            if (!value) {
                message = '나이를 입력해주세요';
                isValid = false;
            } else if (isNaN(value) || Number(value) < 10 || Number(value) > 99) {
                message = '나이는 10-99 사이의 숫자여야 합니다';
                isValid = false;
            }
            break;

        case 'email':
            if (!value) {
                message = '이메일을 입력해주세요';
                isValid = false;
            } else if (!value.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)) {
                message = '올바른 이메일 형식이 아닙니다';
                isValid = false;
            }
            break;

        case 'phone':
            if (!value) {
                message = '전화번호를 입력해주세요';
                isValid = false;
            } else if (!value.match(/^010-\d{3,4}-\d{4}$/)) {
                message = '전화번호 형식(010-XXXX-XXXX)이 맞지 않습니다';
                isValid = false;
            }
            break;
    }

    input.style.border = isValid ? '2px solid green' : '2px solid red';
    showError(input, message);

    if (!isValid && document.activeElement !== input) {
        input.focus();
    }

    return isValid;
}

function validateAll() {
    let isValid = true;
    inputs.forEach(input => {
        if (!validateField(input)) {
            isValid = false;
        }
    });
    return isValid && isIdValid === 1;
}

function handleIdValidationResult(data) {
    const passInput = document.querySelector('#pass');

    switch (data) {
        case 'valid':
            alert('이 아이디는 사용 가능합니다');
            idInput.style.border = '2px solid green';
            idInput.setAttribute('readonly', 'true');
            passInput.focus();
            isIdValid = 1;
            break;

        case 'notValid':
            alert('이 아이디는 사용 불가능합니다');
            idInput.style.border = '2px solid red';
            idInput.value = '';
            idInput.focus();
            isIdValid = -1;
            break;

        default:
            alert('예상치 못한 응답입니다');
            idInput.style.border = '2px solid orange';
            isIdValid = 0;
    }
}

function showError(input, message) {
    let errorElement = input.nextElementSibling;
    if (!errorElement || !errorElement.classList.contains('error-message')) {
        errorElement = document.createElement('div');
        errorElement.classList.add('error-message');
        input.parentNode.insertBefore(errorElement, input.nextSibling);
    }
    errorElement.textContent = message;
    errorElement.style.color = 'red';
}


const style = document.createElement('style');
style.textContent = `
    input[readonly] {
        background-color: #f0f0f0;
    }
    .error-message {
        font-size: 0.8em;
        margin: 2px 0;
    }
`;
document.head.appendChild(style);