<template>
  <div id="test">
    <head>
      <meta name="viewport" content="width=device-width, inital-scale=1" />
    </head>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
      crossorigin="anonymous"
    />

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
          <button type="button" class="btn btn-warning" @click="begin">
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
          <div
            id="resultImg"
            class="my-3 col-lg-6 col-md-8 col-sm-10 col-12 mx-auto"
          ></div>
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
export default {
  data() {
    return {}
  },
  methods: {
    begin() {
      main.style.WebkitAnimation = 'fadeOut 1s'
      main.style.animation = 'fadeOut 1s'
      setTimeout(() => {
        qna.style.WebkitAnimation = 'fadeIn 1s'
        qna.style.animation = 'fadeIn 1s'
        setTimeout(() => {
          main.style.display = 'none'
          qna.style.display = 'block'
        }, 450)
        const qIdx = 0
        goNext(qIdx)
      }, 450)
    },
    setShare() {
      // var resultImg = document.querySelector('#resultImg')
      // var resultAlt = resultImg.firstElementChild.alt
      // const shareTitle = '십이간지 연애유형 결과'
      // const shareDes = infoList[resultAlt].name
      // const shareImage = url + 'img/image-' + resultAlt + '.png'
      // const shareURL = url + 'page/result-' + resultAlt + '.html'
    }
  }
}
// start
const main = document.querySelector('#main')
const qna = document.querySelector('#qna')
const result = document.querySelector('#result')

// 질문개수
const endPoint = 12
const select = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

function calResult() {
  console.log(select)
  const result = select.indexOf(Math.max(...select))
  return result
}

function setResult() {
  const point = calResult()
  const resultName = document.querySelector('.resultname')
  resultName.innerHTML = infoList[point].name

  const resultImg = document.createElement('img')
  const imgDiv = document.querySelector('#resultImg')
  const imgURL = 'img/image-' + point + '.png'
  resultImg.src = imgURL
  resultImg.alt = point
  resultImg.classList.add('img-fluid')
  imgDiv.appendChild(resultImg)

  const resultDesc = document.querySelector('.resultDesc')
  resultDesc.innerHTML = infoList[point].desc
}

function goResult() {
  qna.style.WebkitAnimation = 'fadeOut 1s'
  qna.style.animation = 'fadeOut 1s'

  setTimeout(() => {
    result.style.WebkitAnimation = 'fadeIn 1s'
    result.style.animation = 'fadeIn 1s'
    setTimeout(() => {
      qna.style.display = 'none'
      result.style.display = 'block'
    }, 450)
  })
  setResult()
}

function addAnswer(answerText, qIdx, idx) {
  const a = document.querySelector('.answerBox')
  const answer = document.createElement('button')
  answer.classList.add('answerList')
  answer.classList.add('my-3')
  answer.classList.add('py-3')
  answer.classList.add('mx-auto')
  answer.classList.add('fadeIn')

  a.appendChild(answer)
  answer.innerHTML = answerText

  // 클릭하면 다른버튼들 다 사라짐
  answer.addEventListener(
    'click',
    function () {
      const children = document.querySelectorAll('.answerList')
      for (let i = 0; i < children.length; i++) {
        children[i].disabled = true
        children[i].style.WebkitAnimation = 'fadeOut 0.5s'
        children[i].style.animation = 'fadeOut 0.5s'
      }
      setTimeout(() => {
        const target = qnaList[qIdx].a[idx].type
        for (let i = 0; i < target.length; i++) {
          select[target[i]] += 1
        }

        for (let i = 0; i < children.length; i++) {
          children[i].style.display = 'none'
        }
        goNext(++qIdx)
      }, 450)
    },
    false
  )
}

function goNext(qIdx) {
  if (qIdx === endPoint) {
    goResult()
    return
  }

  const q = document.querySelector('.qBox')
  q.innerHTML = qnaList[qIdx].q
  for (const i in qnaList[qIdx].a) {
    addAnswer(qnaList[qIdx].a[i].answer, qIdx, i)
  }

  // 스테이터스 바 진행도 체크
  const status = document.querySelector('.statusBar')
  status.style.width = (100 / endPoint) * (qIdx + 1) + '%'
}

// data -> 나중에 서버랑 연결할때 data binding 으로 연결한다.
const qnaList = [
  {
    q: '1. 당신의 소중한 반려식물, 두는곳은 어디에? ',
    a: [
      {
        answer: 'a. 햇볕 잘드는 창가',
        type: ['cow', 'tiger', 'dragon', 'chick']
      },
      {
        answer: 'b. 가끔 은은하게 해드는 방구석',
        type: ['mouse', 'rabbit', 'horse', 'snake', 'dog', 'monkey']
      },
      { answer: 'c. 볕안드는 음지', type: ['sheep', 'pig'] }
    ]
  },
  {
    q: '2. 당신의 귀차니즘은? ',
    a: [
      {
        answer: 'a. 밥먹는것도 귀찮아.. 식물키우면 말려죽이고 말거야 ',
        type: ['mouse', 'rabbit', 'tiger', 'monkey']
      },
      {
        answer:
          'b. 귀찮지만.. 다들그러면서 사는거 아니겠어? 가끔은 신경써줄수 있어 ',
        type: ['cow', 'horse', 'snake', 'dog']
      },
      {
        answer: 'c. 파워 부지런인간 관리는 맡겨둬!',
        type: ['sheep', 'dragon', 'chick', 'pig']
      }
    ]
  },
  {
    q: '3. 식물 기르기 고수인가요? ',
    a: [
      {
        answer: 'a. 초보입니다',
        type: ['cow', 'rabbit', 'tiger', 'dog', 'monkey']
      },
      { answer: 'b. 경험은 있습니다', type: ['sheep', 'chick', 'pig'] },
      {
        answer: 'c. 고수입니다. 까다로운 식물도 okay',
        type: ['mouse', 'horse', 'snake', 'dragon']
      }
    ]
  },
  {
    q: '4. 호감가는 고백 멘트를 고른다면?',
    a: [
      {
        answer: 'a. 박력 그 자체, "야 나랑 사귀자" ',
        type: ['cow', 'tiger', 'dragon']
      },
      {
        answer: 'b. 섬세한 "나 너 많이 좋아해, 나랑 사귈래?" ',
        type: ['sheep', 'dog', 'monkey', 'chick', 'pig']
      },
      {
        answer: 'c. 센스있는 "우리 내일은 연인으로 만날까요" ',
        type: ['mouse', 'rabbit', 'horse', 'snake']
      }
    ]
  },
  {
    q: '5. 오늘 연인과 첫 데이트 하는 날이다. 첫 날 허용할 수 있는 스킨십의 범위는?',
    a: [
      {
        answer: 'a. 쑥스러워.. 손 잡기 정도??',
        type: ['cow', 'sheep', 'dog', 'dragon', 'chick']
      },
      {
        answer: 'b. 요즘 시대가 어느 때인데, 가벼운 뽀뽀까지 가능이야',
        type: ['mouse', 'rabbit', 'horse', 'pig']
      },
      {
        answer: 'c. 어차피 내 연인인데 왜?? 모든 스킨십 다 돼!',
        type: ['tiger', 'snake', 'monkey']
      }
    ]
  },

  {
    q: '6. 기념일이 다가온다. 당신은 어떻게 할 것인가? (ex - 100, 200일)',
    a: [
      {
        answer: 'a. 무슨 기념일이야, 그냥 평소처럼 해',
        type: ['dragon', 'chick', 'pig']
      },
      {
        answer: 'b. 맛있는 밥이나 사줄까? 아니면 꽃이라도?',
        type: ['mouse', 'rabbit', 'horse', 'dog']
      },
      {
        answer: 'c. 이미 깜짝 이벤트 다 준비해놨어',
        type: ['cow', 'sheep', 'tiger', 'snake', 'monkey']
      }
    ]
  },
  {
    q: '7. 연인이 오늘 머리를 바꾸고 옷도 새로 샀다',
    a: [
      { answer: 'a. 눈치 못 채고 그냥 있는다', type: ['cow', 'sheep', 'pig'] },
      {
        answer: 'b. 눈치는 채나 말은 꺼내지 않는다',
        type: ['tiger', 'dragon', 'chick']
      },
      {
        answer:
          'c. "오 대박 오늘 머리도 바꾸고 옷도 새로 샀네?"라고 하며 단번에 눈치챈다',
        type: ['mouse', 'rabbit', 'horse', 'snake', 'dog', 'monkey']
      }
    ]
  },
  {
    q: '8. 연인이 데이트가 끝난 후, 집에 데려다 달라고 한다',
    a: [
      {
        answer: 'a. 각자 만났던 장소에서 헤어진다.',
        type: ['mouse', 'dragon', 'chick']
      },
      {
        answer: 'b. 연인이 살고 있는 집의 역이나 정류장까지 데려다준다.',
        type: ['rabbit', 'tiger', 'horse', 'snake', 'dog', 'monkey']
      },
      { answer: 'c. 집 앞까지 데려다준다.', type: ['cow', 'sheep', 'pig'] }
    ]
  },
  {
    q: '9. 연인에게 하루종일 연락이 안 온다..',
    a: [
      {
        answer: 'a. 뭐.. 언젠가 연락 오겠지. 내가 할 일 하며 기다린다',
        type: ['sheep', 'mouse', 'snake', 'chick']
      },
      {
        answer:
          'b. 초조해서 마음 졸이며 인스타, 페북, 카톡 등 모든 SNS 다 들어가본다.',
        type: ['cow', 'rabbit', 'horse', 'pig']
      },
      {
        answer: 'c. 연인에게 연락 올 때까지 무한 카톡, 무한 전화',
        type: ['tiger', 'dog', 'monkey', 'dragon']
      }
    ]
  },
  {
    q: '10. 연인이 연락도 없이 보고싶다고 우리집 앞에 와서 기다린다. ',
    a: [
      {
        answer:
          'a. 아니 갑자기 오면 어떡해.. 하 나 준비도 안 했는데 왜 와. 짜증나네',
        type: ['dragon', 'chick', 'pig']
      },
      {
        answer: 'b. 아 그래?? 나 할 것들 좀만 마무리하고 나갈게',
        type: ['mouse', 'tiger', 'horse', 'snake']
      },
      {
        answer: 'c. 헐 대박, 어디야?? 당장 나갈게!',
        type: ['cow', 'sheep', 'rabbit', 'dog', 'monkey']
      }
    ]
  },
  {
    q: '11. 내가 추구하는 연애관은? ',
    a: [
      {
        answer: 'a. 각자 할 것 하며, 평안하고 안정적인 연애가 좋다.',
        type: ['cow', 'dragon', 'chick', 'pig']
      },
      {
        answer:
          'b. 보고싶을 때 보고, 데이트도 많이 하고 꽁냥꽁냥하는 연애가 좋아.',
        type: ['sheep', 'mouse', 'rabbit', 'horse', 'dog']
      },
      {
        answer: 'c. 연애는 마음가는대로! 불타오르는 연애가 최고지.',
        type: ['tiger', 'snake', 'monkey']
      }
    ]
  },
  {
    q: '12. 연인과 데이트 도중 화를 참을 수 없을만큼 심하게 싸웠다. 어떻게 할 것인가?',
    a: [
      {
        answer:
          'a. "아 화나네. 따라오지마 오늘 집 갈 거니까." 라고 말하며 집으로 간다.',
        type: ['rabbit', 'horse', 'dragon', 'chick']
      },
      {
        answer:
          'b. "야! 너 화내지마. 너 잘못이지. 내가 뭘 잘못했는데?" 소리지르며 끝까지 불같이 화낸다.',
        type: ['mouse', 'tiger', 'snake', 'dog', 'monkey']
      },
      {
        answer: 'c. "아니 그건 아니고, 하.." 조용히 운다.',
        type: ['cow', 'sheep', 'pig']
      }
    ]
  }
]

const infoList = [
  {
    name: '연애 뭐 식은 죽 먹기죠, 연애 재능 만땅 <쥐>',
    desc: '연애의 프로재능러인 당신! 쥐에 해당하는 당신은 연애의 모든 것들을 알고 있어, 쉽게 연애에 성공하는 스타일이랍니다. 주로 포부가 크고 성격이 낙천적인 성향을 가지고 있고, 논리적이면서 두뇌회전이 매우 빠르다고 할 수 있어요. 말재주 또한 좋아 사교성이 좋고 유쾌하지만 화려한 것을 좋아해 허영심을 부리거나 사치를 부리기 때문에 이 점들을 주의해야 돼요. 뛰어난 능력에 자만하는 경우도 종종 있기 때문에 겸손이 필요하답니다! '
  },
  {
    name: '난 너만 봐, 정직한 연애 스타일의 <소>',
    desc: '연애 유형의 진국인 당신! 소에 해당하는 당신은 상대방만 바라보는 해바라기 연애 스타일이랍니다. 매사에 끈기가 있어 연애를 할 때 성실하고 진심을 다하는 성향을 가지고, 온화하며 따뜻한 마음을 가지고 있어요. 한 번 좋아하면 끝까지 좋아하는 의리파지만 자존심이 쌔 은근히 고집이 강하답니다. 또한 욱하는 성질이 있어 이런 부분들을 조심해야 하고, 질투심이 많아 사랑이 집착으로 바뀔 수 있어 주의해야 된답니다! '
  },
  {
    name: '야 사귀자, 사랑을 쟁취하는 <호랑이>',
    desc: '한 번 좋아하는 사람이 있으면 내 사람으로 만드는 당신! 호랑이에 해당하는 당신은 썸부터 사랑까지 한 번에 직진하는 스타일이랍니다. 연애할 때 주로 이끄는 성향을 가지고 있고, 시원시원한 성격과 유쾌한 성격을 가지고 있어요. 대쪽같은 성격에다 실수를 용납하는 대인배지만 유혹에 약하고 허영때문에 들뜨는 성격이라 다른 이성의 접근에 주의해야 돼요. 또한 내가 한 말로 인해 상대방이 상처를 받을 수 있기 때문에, 항상 말 조심이 필요하답니다!'
  },
  {
    name: '이건 어때? 난 어때? 독특한 매력을 가진 <토끼>',
    desc: '상대방을 홀리게 만드는 당신! 신비롭고 독특한 매력을 가져, 상대방이 푹 빠지게 만드는 스타일이에요. 직감이 발달되어 손재주가 뛰어난 편이고, 연애할 때 통통 튀는 모습들을 많이 보여준답니다. 유순하고 낙천적인 성향이지만, 자칫잘못하면 상대방에게 천진난만한 아이처럼 보일 수 있는 점을 주의해야 돼요. 또한 감정 기복이 심한 편이라 변덕스러운 마음을 가질 수 있어, 이런 부분들에 신경을 쓴다면 좋은 연애를 할 수 있어요.'
  },
  {
    name: '결혼은 언제쯤 할까 신혼여행은 어디로?, 이상적인 연애관의 소유자 <용>',
    desc: '상대방과의 미래를 꿈꾸는 당신! 현실적인 연애보다는, 미래에 대한 이상적인 연애관을 가지고 있어요. 매일매일 사랑하는 사람과 보낼 미래를 생각하기 때문에, 좋은 신랑감이 될 수 있는 자질이 있답니다. 또한 일적으로나 사랑으로나 야망이 커, 크게 성공할 확률이 높고 리더십이 매우 뛰어나답니다. 다만 높은 이상에 비해서 현실이 자신의 마음에 들지 않으면 허세를 부리거나 사람들을 무시하는 경우가 많으니 이점들을 주의하면 좋을 것 같아요!'
  },
  {
    name: '기념일에 무슨 이벤트 할까, 이벤트 플래너 <뱀>',
    desc: '상대방의 기념일을 챙기며 깜짝 놀라게 하는 당신!, 아이디어가 뛰어나고 남다른 감각이 있어, 상대방에게 많은 이벤트를 해주는 스타일이에요. 연애할 때 눈치도 빠르고, 감동을 줄 수 있어 상대방이 매일 행복하답니다. 전체적으로 화려한 연애를 하지만, 은근히 비밀이 많고 우유부단한 성격을 가지고 있답니다. 또한 귀가 매우 얇아 남의 이야기를 곧이 곧대로 믿어, 상대방을 의심하기도 하기 때문에 조심해야 된답니다!'
  },
  {
    name: '어 오늘 머리 바꿨네 잘 어울린다, 센스가 뛰어난 <말>',
    desc: '연애할 때, 센스가 넘쳐나는 당신! 말재주가 뛰어나며 처세술이 뛰어나, 상대방의 호감을 금방 얻는답니다. 낙천적이고 유연한 성격을 가지고 있으며, 구속받는 것을 싫어하는 스타일이에요. 소중한 사람들을 위해 희생할 줄도 알지만, 쾌략 지향적인 경우가 많아 사치와 허영에 물드는 경우가 다반수입니다. 한탕 주의에 빠져 쉽게 망가질 수 있기 때문에 이 점을 주의해야 된답니다!'
  },
  {
    name: '난 너랑 하는 것들 다 좋아, 순종적인 <양>',
    desc: '연애할 때 모든 걸 맞춰주려고 하는 당신! 다정한 느낌과 상냥한 모습을 가지고 있어, 상대방 입장에서 편안한 느낌을 받을 수 있답니다. 타오르는 연애보다는 안정적인 연애를 추구하고, 상대방에게 마음을 여는 순간 진심으로 사랑에 푹 빠질 수 있습니다. 또한 강인한 생존력과 수완이 좋으나, 한편으로는 자신이 원하는 바를 모두 쟁취하려는 고집이 있어요. 이 고집이 심해지면 다른 사람 눈에서는 계산적인 사람처럼 보여 매력을 잃을 수 있습니다!'
  },
  {
    name: '난 당신의 연예인, 끼가 넘치는 재주꾼 <원숭이>',
    desc: '지치고 힘들 때 언제나 행복을 주는 당신! 정말 끼가 많고 말재주가 많다보니 팔방미인이라는 소리를 많이 듣는 편이랍니다. 또한 다재다능하고 총명하여 어떤 일이든지 잘할 수 있고, 유머 감각도 뛰어나 주변 사람들이 많이 따르는 마당발 타입입니다. 연애할 때 상대방의 기분을 잘 맞춰주고 즐거운 분위기를 유지하나, 다소 본인의 체면을 중시하는 경향이 있어요. 이 점 때문에 잔머리를 굴리다 타인에게 교활하다는 인상을 주게 될 수도 있기 때문에 조금 조심하면 좋을 것 같아요!'
  },
  {
    name: '이건 이거고 저건 저거지 , 공과사 확실한 <닭>',
    desc: '깔끔한 연애를 추구하며 맺고 끊는 것이 확실한 당신! 단아하고 고고한 이미지를 가지고 있어 우아한 매력을 표출하는 편이랍니다. 집중력과 기억력이 좋아 상대방과 있었던 일들을 다 기억해주는 편이고, 겉으로는 무뚝뚝하나 은근히 감수성이 풍부하답니다. 다만 자신이 생각한 선을 벗어나면 냉소적으로 바뀌고, 평소 직설적인 편이라 차갑다는 인상을 많이 줄 수 있어요. 섬세한 사람이기 때문에 예민한 경우가 많으니 마음을 평안히 다스리고, 상대방에게 좀만 더 따뜻하게 대하면 좋을 것 같아요!'
  },
  {
    name: '난 네가 좋아 너무 좋아, 애교만땅 <개>',
    desc: '애교쟁이 그 자체인 당신! 둥글둥글하게 처세를 잘 하고, 붙임성이 매우 좋아 사교성이 매우 뛰어난 편이랍니다. 또한 궁금한 것을 참지 못해 호기심이 많고, 강아지같이 상대방에게 찰싹 달라붙는 것을 좋아하는 스타일이에요. 성격이 매우 쾌활하나, 감정의 기복이 은근히 심해 상대방이 하는 말에 대해 크게 상처받는 편입니다. 또한 성격이 급한 경우가 많아, 차분하게 행동을 하지 못하는 편이기 때문에 이 점을 좀만 조심하면 좋을 것 같아요! '
  },
  {
    name: '산은 산이요 연애는 연애로다, 느긋하고 온순한 <돼지>',
    desc: '단아하고 고상한 품격을 가진 당신! 마음을 먹을 때 느긋하게 행동하고, 상대방에 대한 배려가 상당히 많아 온화한 성품을 가지고 있어요. 또한 솔직한 성격으로 신뢰를 받을 수 있는 타입이며, 어떠한 일을 하든 책임감이 강해 무사히 마무리한답니다. 현실적인 성향이 강해 실리를 잘 챙기나, 표현력이 다소 부족해 상대방이 사랑받는다는 감정을 못 느낄수도 있어요. 은근히 질투심이 강하고 고지식한 면이 강해, 누가 자기에게 잘못하면 지구 끝까지 복수 할 수 있을 정도이기 때문에 이 점을 조심해야 한답니다! '
  }
]

// share
// const url = 'https://twelvelovetype.netlify.app/'
</script>

<style scoped>
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
  background: -webkit-linear-gradient(
    top,
    #d1c6ab 0%,
    #c5b795 40%,
    #b1a075 100%
  );
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
