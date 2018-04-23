import {server} from '@/tools/servers'

export class userManagement {
  static AddNewUser(param) {
    return server.connection('POST' , `/api/user`, param)
  }
  static UpdataUser(param) {
    return server.connection('PUT',`/api/user`,param)
  }
  static DeleteUser(param) {
    return server.connection('DELETE', `/api/user`,param)
  }
  static GetUser(param) {
    return server.connection('GET',`/api/user`,param)
  }
  static GetUserList(search = {}) {
    return server.connection('GET', `/api/users`)
  }
  static GetUserList(param) {
    console.log(param);
    return server.connection('GET', `/api/users`, param)
  }
  static CheckAccount(param) {
    return server.connection('GET', `/api/account/validate`, param)
  }
  static ChangeUserLockState(param) {
    return server.connection('PUT',`/api/lockState`,param)
  }
  static GetRoleSelectData(param) {
    return server.connection('GET',`/api/allRoles`,param)
  }
  static GetOrgSelectData(param) {
    return server.connection('GET',`/api/userAllOrgs`,param)
  }
}
