import request from '@/utils/request'

export function getConfig(data) {
  return request({
    url: '/hk/wwz/key/findKey',
    method: 'post',
    data: data || {}
  })
}
