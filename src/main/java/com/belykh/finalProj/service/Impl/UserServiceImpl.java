package com.belykh.finalProj.service.Impl;

import com.belykh.finalProj.dao.DAOFactory;
import com.belykh.finalProj.dao.UserDAO;
import com.belykh.finalProj.entity.UserInfo;
import com.belykh.finalProj.entity.dbo.UserDBO;
import com.belykh.finalProj.exception.DAOException;
import com.belykh.finalProj.exception.ServiceException;
import com.belykh.finalProj.service.UserService;
import com.belykh.finalProj.util.MD5Util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
public class UserServiceImpl implements UserService {

    /** The dao factory. */
    public static DAOFactory daoFactory = new DAOFactory();


    /* (non-Javadoc)
     * @see com.belykh.finalProj.service.UserService#Authorization(java.lang.String, java.lang.String)
     */
    @Override
    public UserDBO Authorization(String login, String password) throws ServiceException {
        UserDBO result = null;
        String passHash = MD5Util.getInstance().getMD5Hash(password);
        UserDAO dao = daoFactory.getUserDAO();
        try {
            UserDBO userDBO = dao.findUserByLogin(login);
            if(userDBO !=null&&userDBO.getPass().toUpperCase().equals(passHash.toUpperCase())){
                result = userDBO;
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.belykh.finalProj.service.UserService#SignUp(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean SignUp(String login, String password, String passwordRepeat, String email, String firstName, String lastName) throws ServiceException {
        boolean result = false;
            if(password.equals(passwordRepeat)){
                UserDAO dao = daoFactory.getUserDAO();
                try {
                    if(dao.isLoginFree(login)){
                        UserDBO newUser = new UserDBO(0l,login,MD5Util.getInstance().getMD5Hash(password),email,firstName,lastName,1,new BigDecimal(0));
                        result= dao.addUser(newUser);
                    }
                } catch (DAOException e) {
                    throw new ServiceException(e);
                }
            }
        return result;
    }

    /* (non-Javadoc)
     * @see com.belykh.finalProj.service.UserService#findUsersInfo()
     */
    @Override
    public List<UserInfo> findUsersInfo() throws ServiceException {
        List<UserInfo> result = new ArrayList<>();
        UserDAO dao = daoFactory.getUserDAO();
        try {
            List<UserDBO> userList = dao.findAllUsers();
            for(UserDBO user:userList){
                result.add(new UserInfo(user.getId(),user.getLogin(),user.getEmail(),user.getFirstName(),user.getLastName(),user.getBalance()));
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.belykh.finalProj.service.UserService#findUserInfo(java.lang.String)
     */
    @Override
    public UserInfo findUserInfo(String login) throws ServiceException {
        UserInfo result = null;
        UserDAO dao = daoFactory.getUserDAO();
        try {
            UserDBO user = dao.findUserByLogin(login);
            if(user !=null){
                result = new UserInfo(user.getId(),user.getLogin(),user.getEmail(),user.getFirstName(),user.getLastName(),user.getBalance());
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.belykh.finalProj.service.UserService#findUserInfoById(java.lang.Long)
     */
    @Override
    public UserInfo findUserInfoById(Long id) throws ServiceException {
        UserInfo result = null;
        UserDAO dao = daoFactory.getUserDAO();
        try {
            UserDBO user = dao.findUserById(id);
            if(user !=null){
                result = new UserInfo(user.getId(),user.getLogin(),user.getEmail(),user.getFirstName(),user.getLastName(),user.getBalance());
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.belykh.finalProj.service.UserService#changeUserInfo(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean changeUserInfo(String login,String email, String firstName,String lastName) throws ServiceException {
        boolean result = false;
        UserDAO dao = daoFactory.getUserDAO();
        try {
            UserDBO user= dao.findUserByLogin(login);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            result= dao.changeUserInfo(user);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

    /* (non-Javadoc)
     * @see com.belykh.finalProj.service.UserService#changePassword(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public boolean changePassword(String login, String newPass, String newPassRepeat) throws ServiceException {
        boolean result = false;
        if(newPass.equals(newPassRepeat)){
            UserDAO dao = daoFactory.getUserDAO();
            try {

                result = dao.changePassword(login, MD5Util.getInstance().getMD5Hash(newPass));

            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.belykh.finalProj.service.UserService#changeBalance(java.lang.Long, java.math.BigDecimal)
     */
    @Override
    public boolean changeBalance(Long id, BigDecimal balance) throws ServiceException {
        boolean result;
        UserDAO dao = daoFactory.getUserDAO();
        try {
            result = dao.changeMoney(id, balance);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return result;
    }

}
