import request from '@/utils/request'

export function getEncrypt(data) {
  return request({
    url: '//hk/wwz/key/findKey',
    method: 'post',
    data: data || {}
  })
}
