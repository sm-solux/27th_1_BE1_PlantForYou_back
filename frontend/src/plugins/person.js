export default {
  install(app, options) {
    const person = {
      name: 'BE1',
      say() {
        alert(this.name)
      },
      ...options
    }
    app.config.globalProperties.$person = person
    app.provide('person', person)
  }
}
