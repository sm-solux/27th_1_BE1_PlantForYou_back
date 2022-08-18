<template>
  <header>
    <div class="innerHeader" id="main">
      <div class="util">
        <ul>
          <li>
            <img
              src="../assets/menu-top.png"
              alt="home"
              @click="menu"
              style="cursor: pointer"
            />
          </li>
        </ul>
      </div>

      <span class="logo">
        <RouterLink to="/">
          <img src="../assets/logo.jpg" alt="로고" />
        </RouterLink>
      </span>
    </div>

    <div class="related" v-if="!loggedIn">
      <a :href="GOOGLE_AUTH_URL" id="google">
        <img src="../assets/google.png" />
      </a>
    </div>

    <div class="related" v-if="loggedIn" style="margin-top: 25px">
      <span class="d-flex flex-row">
        <span style="margin-top: 7px">
          <a
            href="#"
            title="닉네임을 설정해주세요!"
            style="color: inherit; text-decoration: none"
            ><strong @click="setNickname">{{ userName }}</strong></a
          >
          님 안녕하세요
        </span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-outline-primary" @click="logout">
          로그아웃
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-outline-danger" @click="withdraw">
          회원탈퇴
        </button>
      </span>
    </div>
  </header>

  <div class="may">
    <div class="back">
      <strong>Menu</strong>
    </div>
    <ul>
      <li>
        <RouterLink to="/about"> <span>식물추천</span> </RouterLink>
      </li>
      <li>
        <RouterLink to="/plants"><span>식물사전</span></RouterLink>
      </li>
      <li>
        <RouterLink to="/posts"><span>커뮤니티</span></RouterLink>
      </li>
    </ul>
  </div>
</template>

<script>
import $ from 'jquery'
import constant from '@/constant'
import * as userApi from '@/api/user'

export default {
  data() {
    return {
      loggedIn: this.$store.state.auth.loggedIn,
      GOOGLE_AUTH_URL: constant.GOOGLE_AUTH_URL,
      userName: '',
      open: false
    }
  },
  created() {
    if (this.loggedIn) {
      userApi.getName().then((res) => {
        this.userName = res.data.userNickname
      })
    }
  },
  methods: {
    menu() {
      // 메뉴 이동 jquery 코드
      $('.innerHeader .util ul').on('click', () => {
        if (this.open) {
          setTimeout(() => {
            $('.may').removeClass('on')
            this.open = false
          }, 200)
        } else {
          setTimeout(() => {
            $('.may').toggleClass('on')
            this.open = true
          }, 300)
        }
      })
    },
    logout() {
      this.$store.dispatch('auth/logout')
      location.href = '/'
    },
    withdraw() {
      if (
        confirm(
          '탈퇴 시 작성한 모든 글과 댓글이 삭제됩니다. 정말 탈퇴하시겠습니까?'
        ) === false
      ) {
        return
      }
      userApi
        .withdraw()
        .then(() => this.logout())
        .catch((err) => console.log(err.config))
      alert('탈퇴가 완료되었습니다.')
      setTimeout(() => {
        this.$router.push('/')
      }, 1000)
    },
    setNickname() {
      const nickname = prompt('닉네임을 입력해주세요')
      userApi.setNickname(nickname).then(() => {
        this.userName = nickname
        alert('닉네임이 ' + nickname + '(으)로 변경되었습니다.')
      })
    }
  }
}
</script>

<style scoped>
@charset "utf-8";
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');

/*
reset*/
* {
  margin: 0;
  padding: 0;
  font-family: 'Nanum Gothic', sans-serif !important;
}

li {
  list-style: none;
}

a {
  color: inherit;
  text-decoration: none;
}

/*layout*/
body {
  font-family: 'Nanum Gothic', sans-serif;
  font-size: 16px;
  color: #333;
  line-height: 1.25;
}

header {
  width: 100%;
  height: 90px;
  background: #fff;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  position: relative;
}

.innerHeader .util ul {
  width: 218px;
  height: 100%;
  float: left;
  padding-left: 25px;
  margin-top: 36px;
}

.innerHeader .util ul li {
  float: left;
}

.innerHeader .logo {
  width: 270px;
  line-height: 90px;
  position: absolute;
  left: 50%;
  top: 5px;
  transform: translateX(-50%);
}

.innerHeader .logo #google {
  display: block;
  width: 100%;
  height: 100%;
}

.related {
  width: 400px;
  float: right;
}

.related #google {
  display: block;
  float: right;
  margin-right: 20px;
  margin-top: 24px;
  color: white;
}

.may {
  z-index: 1;
}

.may strong {
  color: beige;
}

.may ul li {
  color: beige;
  border-bottom: 1px solid white;
}

.may ul li span:hover {
  color: beige;
  border-bottom: 1px solid white;
}

.may ul li:nth-child(2) {
  border-bottom: 1px solid white;
}

.may ul li:nth-child(3) {
  border-bottom: 1px solid white;
}

#may1 strong {
  color: beige;
}

#may1 ul li {
  color: darkslategray;
  border-bottom: 1px solid darkslategray;
}

#may1 ul li:nth-child(2) {
  border-bottom: 1px solid darkslategray;
}

#may1 ul li:nth-child(3) {
  border-bottom: 1px solid darkslategray;
}

.may.on {
  width: 185px;
  left: 5%;
}

.may {
  position: absolute;
  width: 0;
  height: 300px;
  top: 37%;
  transform: translateY(-50%);
  left: -1000px;
  transition: all 0.7s;
}

.may > .back {
  display: block;
  width: 135px;
  height: 135px;
  border-radius: 50%;
  background: #1d3a09;
  text-align: center;
  margin-bottom: 30px;
}

.may strong {
  line-height: 2.25;
  font-style: italic;
  font-size: 55px;
  letter-spacing: -2px;
}

.may p {
  color: #fff;
}

.may ul {
  width: 185px;
  height: auto;
}

.may ul li {
  line-height: 40px;
  font-size: 14px;
  letter-spacing: 5px;
  font-weight: 600;
  animation-name: mayLi;
  animation-duration: 0.8s;
}

.may ul li:nth-child(2) {
  animation-duration: 1.2s;
}

.may ul li:nth-child(3) {
  animation-duration: 1.5s;
}

.may ul li .back {
  display: block;
}

.may h3 {
  position: absolute;
  top: 100%;
  right: 25px;
  font-size: 40px;
  animation-name: mayh3;
  animation-duration: 1.8s;
}

@keyframes mayLi {
  0% {
    opacity: 0;
    letter-spacing: -5px;
  }

  30% {
    opacity: 0;
    letter-spacing: -5px;
  }

  50% {
    opacity: 0;
    letter-spacing: -5px;
  }

  100% {
    opacity: 1;
    letter-spacing: 5px;
  }
}

@keyframes mayh3 {
  0% {
    opacity: 0;
    top: 95%;
  }

  30% {
    opacity: 0;
    top: 95%;
  }

  100% {
    opacity: 1;
    top: 100%;
  }
}
</style>
