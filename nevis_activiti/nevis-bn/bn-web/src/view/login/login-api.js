import {server} from '@/tools/servers'

/**
 * 登陆
 */
export class LoginApi {
  static login (params) {
    return server.connectionPost('POST', '/api/login', params)
  }

  static logout () {
    return server.connection('GET', '/api/logout')
  }
}
