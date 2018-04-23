import {Auth} from '../../store/user/auth';

/**
 * @author 李佳莉
 * 权限判断
 * @param auth 权限标识
 * @returns {boolean}
 */
  function hasPermission(auth) {
  let permissions = Auth.getAuthorities();
  if(permissions && isArray(permissions)){
    return permissions.find((value, index, arr) => value.authority == auth.id) != undefined;
  }
  return false;
}

function isArray(something){
  return Object.prototype.toString.call(something) === '[object Array]';
}



const validatator ={
   valid : hasPermission
}

export default validatator;
