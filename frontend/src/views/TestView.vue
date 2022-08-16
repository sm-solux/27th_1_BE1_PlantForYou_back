<template>
  <div id="test">

    <head>
      <meta name="viewport" content="width=device-width, inital-scale=1" />
    </head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous" />

    <body class="body1">
      <div class="container">
        <section id="main3" class="mx-auto my-5 py-5 px-3">
          <h1>내 운명의 반려식물은?</h1>
          <div class="col-lg-6 col-md-8 col-sm-10 col-12 mx-auto">
            <img src="../assets/plants.png" alt="mainImage" class="img-fluid" />
          </div>
          <p>
            내 운명의 반려식물은 무엇일까? <br />
            두구두구 나에게 딱맞는 반려식물을 만나보세요!
          </p>
          <button type="button" class="btn btn-warning" onclick="js:begin()">
            시작하기
          </button>
        </section>
        <section id="qna">
          <div class="status mx-auto mt-5">
            <div class="statusBar"></div>
          </div>

          <div class="qBox mt-5 my-2 pt-3 py-2 mx-auto"></div>
          <div class="answerBox"></div>
        </section>
        <section id="result" class="mx-auto my-5 py-5 px-3">
          <h1>당신의 결과는?!</h1>
          <div class="resultname"></div>
          <div id="resultImg" class="my-3 col-lg-6 col-md-8 col-sm-10 col-12 mx-auto"></div>
          <div class="resultDesc"></div>
          <button type="button" class="kakao mt-3 py-2 px-3" onclick="">
            공유하기
          </button>
        </section>
      </div>
    </body>
  </div>
</template>
<script>

// import 'bootstrap/dist/css/bootstrap.min.css';
// import 'bootstrap-icons/font/bootstrap-icons.css';
// import 'bootstrap/dist/js/bootstrap.js';

//start
const main = document.querySelector('#main');
const qna = document.querySelector('#qna');
const result = document.querySelector('#result');

/*질문개수*/
const endPoint = 12;
const select = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];

function calResult() {
  console.log(select);
  var result = select.indexOf(Math.max(...select));
  return result;
}

function setResult() {
  let point = calResult();
  const resultName = document.querySelector('.resultname');
  resultName.innerHTML = infoList[point].name;

  var resultImg = document.createElement('img');
  const imgDiv = document.querySelector('#resultImg');
  var imgURL = 'img/image-' + point + '.png';
  resultImg.src = imgURL;
  resultImg.alt = point;
  resultImg.classList.add('img-fluid');
  imgDiv.appendChild(resultImg);

  const resultDesc = document.querySelector('.resultDesc');
  resultDesc.innerHTML = infoList[point].desc;
}

function goResult() {
  qna.style.WebkitAnimation = 'fadeOut 1s';
  qna.style.animation = 'fadeOut 1s';

  setTimeout(() => {
    result.style.WebkitAnimation = 'fadeIn 1s';
    result.style.animation = 'fadeIn 1s';
    setTimeout(() => {
      qna.style.display = 'none';
      result.style.display = 'block';
    }, 450);
  });
  setResult();
}

function addAnswer(answerText, qIdx, idx) {
  var a = document.querySelector('.answerBox');
  var answer = document.createElement('button');
  answer.classList.add('answerList');
  answer.classList.add('my-3');
  answer.classList.add('py-3');
  answer.classList.add('mx-auto');
  answer.classList.add('fadeIn');

  a.appendChild(answer);
  answer.innerHTML = answerText;

  /*클릭하면 다른버튼들 다 사라짐*/
  answer.addEventListener(
    'click',
    function () {
      var children = document.querySelectorAll('.answerList');
      for (let i = 0; i < children.length; i++) {
        children[i].disabled = true;
        children[i].style.WebkitAnimation = 'fadeOut 0.5s';
        children[i].style.animation = 'fadeOut 0.5s';
      }
      setTimeout(() => {
        var target = qnaList[qIdx].a[idx].type;
        for (let i = 0; i < target.length; i++) {
          select[target[i]] += 1;
        }

        for (let i = 0; i < children.length; i++) {
          children[i].style.display = 'none';
        }
        goNext(++qIdx);
      }, 450);
    },
    false,
  );
}

function goNext(qIdx) {
  if (qIdx === endPoint) {
    goResult();
    return;
  }

  var q = document.querySelector('.qBox');
  q.innerHTML = qnaList[qIdx].q;
  for (let i in qnaList[qIdx].a) {
    addAnswer(qnaList[qIdx].a[i].answer, qIdx, i);
  }

  /*스테이터스 바 진행도 체크 */
  var status = document.querySelector('.statusBar');
  status.style.width = (100 / endPoint) * (qIdx + 1) + '%';
}

function begin() {
  main.style.WebkitAnimation = 'fadeOut 1s';
  main.style.animation = 'fadeOut 1s';
  setTimeout(() => {
    qna.style.WebkitAnimation = 'fadeIn 1s';
    qna.style.animation = 'fadeIn 1s';
    setTimeout(() => {
      main.style.display = 'none';
      qna.style.display = 'block';
    }, 450);
    let qIdx = 0;
    goNext(qIdx);
  }, 450);
}


// share
const url = 'https://twelvelovetype.netlify.app/';

function setShare() {
  var resultImg = document.querySelector('#resultImg');
  var resultAlt = resultImg.firstElementChild.alt;
  const shareTitle = '십이간지 연애유형 결과';
  const shareDes = infoList[resultAlt].name;
  const shareImage = url + 'img/image-' + resultAlt + '.png';
  const shareURL = url + 'page/result-' + resultAlt + '.html';

  Kakao.Link.sendDefault({
    objectType: 'feed',
    content: {
      title: shareTitle,
      description: shareDes,
      imageUrl: shareImage,
      link: {
        mobileWebUrl: shareURL,
        webUrl: shareURL,
      },
    },

    buttons: [
      {
        title: '결과확인하기',
        link: {
          mobileWebUrl: shareURL,
          webUrl: shareURL,
        },
      },
    ],
  });
}

</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Cute+Font&display=swap');



/* main */
#main3 {
  background-color: whitesmoke;
  width: 80%;
  text-align: center;
  border-radius: 20px;
  left: 50%;
  top: 50%;
  font-family: 'Cute Font', cursive !important;
}

h1 {
  font-size: 42px;
  padding-top: 20px;
}

.container img {
  padding: 10% 30% 10% 30%;
}

p {
  font-size: 23px;
}

button {
  margin-top: 5px;
}

.container .main .img-fluid {
  width: 100px;
  height: 200px;
}

/* qna */
#qna {
  display: none;
}

.qBox {
  /* background-color: whitesmoke;*/
  text-align: center;
  border-radius: 20px;
  font-size: 30px;
  font-weight: 500;
  width: 80%;
  color: whitesmoke;
}

.answerList {
  background-color: whitesmoke;
  border-radius: 20px;
  display: block;
  width: 80%;
  border: 0px;
  font-size: 20px;
  color: #545454;
}

.answerList:hover,
.answerList:focus {
  background-color: #5f935d;
  color: whitesmoke;
}

.status {
  height: 20px;
  width: 80%;
  border-radius: 20px;
  background-color: white;
}

.statusBar {
  height: 100%;
  border-radius: 20px;
  /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#d1c6ab+0,c5b795+40,b1a075+100 */
  background: #d1c6ab;
  /* Old browsers */
  background: -moz-linear-gradient(top, #d1c6ab 0%, #c5b795 40%, #b1a075 100%);
  /* FF3.6-15 */
  background: -webkit-linear-gradient(top,
      #d1c6ab 0%,
      #c5b795 40%,
      #b1a075 100%);
  /* Chrome10-25,Safari5.1-6 */
  background: linear-gradient(to bottom, #d1c6ab 0%, #c5b795 40%, #b1a075 100%);
  /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#d1c6ab', endColorstr='#b1a075', GradientType=0);
  /* IE6-9 */
}

/* default */
.body1 {
  background-image: url(../assets/fern-1250903.jpg);
}

* {
  font-family: 'Cute Font', cursive;
}

/* animation */
@keyframes fadeIn {
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
}

@keyframes fadeOut {
  from {
    opacity: 1;
  }

  to {
    opacity: 0;
  }
}

@-webkit-keyframes fadeIn {
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
}

@-webkit-keyframes fadeOut {
  from {
    opacity: 1;
  }

  to {
    opacity: 0;
  }
}

.fadeIn {
  animation: fadeIn;
  animation-duration: 0.5s;
}

.fadeOut {
  animation: fadeOut;
  animation-duration: 0.5s;
}

/* result */
#result {
  display: none;
  background-color: whitesmoke;
  width: 80%;
  text-align: center;
  border-radius: 20px;
}

.resultname {
  font-size: 26px;
}

.resultDesc {
  font-size: 20px;
}

.kakao {
  color: white;
  background-color: #fee500;
  font-size: 20px;
  border: 0px;
  border-radius: 20px;
}

.kakao:hover,
.kakao:focus {
  background-color: whitesmoke;
  color: #fee500;
}
</style>
