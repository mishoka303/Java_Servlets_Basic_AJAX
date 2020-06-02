function postLogin() {
    //debugger
    const formData = toJSONString(document.querySelector('form.login-LoginForm'));
    var element = document.getElementById('errMsg');
    element.innerText = "";

    fetch('loginSubmit', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json, accepts'
        },
        body: formData
    })
    .then((response) => {
        return response.json();
    })
    .then((data) => {
        console.log(data);
        element.innerText = data.message;
    });

    return false;
}

function postRegister() {
    //debugger
    const formData = toJSONString(document.querySelector('form.register-RegisterForm'));
    var element = document.getElementById('errMsg');
    element.innerText = "";

    fetch('registerSubmit', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json, accepts'
        },
        body: formData
    })
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            element.innerText = data.message;
        });

    return false;
}

function toJSONString( form ) {
    var obj = {};
    var elements = form.querySelectorAll( "input, select, textarea" );
    for( var i = 0; i < elements.length; ++i ) {
        var element = elements[i];
        var name = element.name;
        var value = element.value;

        if( name ) {
            obj[ name ] = value;
        }
    }

    return JSON.stringify( obj );
}
