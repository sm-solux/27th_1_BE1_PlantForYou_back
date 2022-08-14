<!-- eslint-disable no-tabs -->
<template>
	<div>
		<h2>식물사전</h2>
		<p>{{ $route.path }}</p>
		<p>{{ $route.name }}</p>
		<button class="btn btn-primary" @click="$router.push('/')">
			Home으로 이동
		</button>
	</div>
</template>

<script setup>
import { useRoute } from 'vue-router';
import { useCounterStore } from '@/store/counter';

const route = useRoute();
console.log('route.path: ', route.path);

const store = useCounterStore();
</script>

<style lang="scss" scoped></style>
