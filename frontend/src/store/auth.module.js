import constant from '@/constant'
const accessToken = localStorage.getItem(constant.ACCESS_TOKEN)
const initialState = accessToken ? { loggedIn: true } : { loggedIn: false }

export const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    logout({ commit }) {
      localStorage.removeItem(constant.ACCESS_TOKEN)
      localStorage.removeItem(constant.REFRESH_TOKEN)
      commit('logout') // mutations의 logout 호출
    }
  },
  mutations: {
    logout(state) {
      state.loggedIn = false
    }
  }
}
