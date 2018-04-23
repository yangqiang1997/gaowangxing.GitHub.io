import {server} from './servers'

export class commonApi {
  static getUserMenu() {
    return server.connection('GET', '/api/menus')
  }

}
