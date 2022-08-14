import { useAlertStore } from '@/store/alert'
import { storeToRefs } from 'pinia'

export const useAlert = () => {
  const { alerts } = storeToRefs(useAlertStore())
  console.log('alerts: ', alerts.value)
  const { vAlert, vSuccess } = useAlertStore()
  return {
    alerts,
    vAlert,
    vSuccess
  }
}
