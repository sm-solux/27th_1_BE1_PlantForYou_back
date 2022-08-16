
<template>
	<header>
		<div class="innerHeader" id="main">
			<div class="util">
				<ul>
					<li><img src="../assets/menu-top.png" alt="home" /></li>
				</ul>
			</div>

			<span class="logo">
				<RouterLink to="/">
					<img src="../assets/logo.jpg" alt="로고" />
				</RouterLink>
			</span>
		</div>
		<div class="related">

			<a href="#">
				<p>G</p>
			</a>
		</div>
	</header>


</template>

<script setup>

//글쓰기 버튼
// import { useRouter } from 'vue-router';

// const router = useRouter();
// const goPage = () => {
// 	router.push({
// 		name: 'PostCreate',
// 	});
// };

// 메뉴 이동 jquery 코드
$(function () {
	$('.innerHeader .util ul').on('click', function () {
		$('.may').toggleClass('on');
	});
	$('.main').on('click', function () {
		$('.may').removeClass('on');
	});
});
</script>

<style>

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
	line-height: 90px;
	margin-left: 11.5px;
	margin-top: 18px;

}

.innerHeader .util ul li {
	float: left;
}

.innerHeader .logo {
	width: 270px;
	line-height: 90px;
	position: absolute;
	left: 46%;
	top: -23px;
	transform: translateX(-50%);
}

.innerHeader .logo a {
	display: block;
	width: 100%;
	height: 100%;
}

.related {
	width: 270px;
	float: right;

}

.related a {
	border-radius: 50%;
	display: block;
	float: right;
	margin-right: 25px;
	margin-top: 21px;
	padding: 10px 15px;
	border: 1px solid #ddd;
	color: white;
	font-weight: 800;
	background-color: #375b1e;
	;
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

.may>.back {
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





<!-- <template>
	<header>
		<nav class="navbar navbar-expand-sm navbar-dark bg-primary">
			<div class="container-fluid">
				<RouterLink class="navbar-brand" to="/">PlantforYou</RouterLink>
				<button
					class="navbar-toggler"
					type="button"
					data-bs-toggle="collapse"
					data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent"
					aria-expanded="false"
					aria-label="Toggle navigation"
				>
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto">
						<li class="nav-item">
							<RouterLink class="nav-link" active-class="active" to="/">
								Home
							</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink
								class="nav-link"
								active-class="active"
								to="/recommend"
							>
								식물추천
							</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink
								class="nav-link"
								active-class="active"
								to="/dictionary"
							>
								식물사전
							</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink class="nav-link" active-class="active" to="/posts">
								게시글
							</RouterLink>
						</li>
						<li class="nav-item">
							<RouterLink class="nav-link" active-class="active" to="/nested">
								Nested
							</RouterLink>
						</li>
					</ul>
					<div class="d-flex" role="search">
						<button class="btn btn-outline-light" type="button" @click="goPage">
							글쓰기
						</button>
					</div>
				</div>
			</div>
		</nav>
	</header>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
const goPage = () => {
	router.push({
		name: 'PostCreate',
	});
};
</script>

<style lang="scss" scoped></style> -->
