package com.belykh.finalProj.dao.impl;

import com.belykh.finalProj.dao.FlowerDAO;
import com.belykh.finalProj.entity.dbo.FlowerDBO;
import com.belykh.finalProj.exception.DAOException;
import com.belykh.finalProj.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class FlowerDAOImpl.
 */
public class FlowerDAOImpl implements FlowerDAO {

    private static final String SQL_FIND_FLOWER_BY_ID="SELECT `flowerType`.`id`,`flowerType`.`name` FROM `flowerType` WHERE `flowerType`.`id`=?";
    private static final String SQL_FIND_FLOWER_BY_NAME="SELECT `flowerType`.`id`,`flowerType`.`name` FROM `flowerType` WHERE `flowerType`.`name`=?";
    private static final String SQL_FIND_ALL_FLOWERS="SELECT `flowerType`.`id`,`flowerType`.`name` FROM `flowerType` ";
    private static final String SQL_ADD_FLOWER = "INSERT INTO `flowerType` (`name`) VALUES (?)";

    private static final String FLOWER_ID="id";
    private static final String FLOWER_NAME="name";

    /* (non-Javadoc)
     * @see com.belykh.finalProj.dao.FlowerDAO#findFlowerById(java.lang.Long)
     */
    @Override
    public FlowerDBO findFlowerById(Long id) throws DAOException {
        FlowerDBO result = null;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_FLOWER_BY_ID)) {
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                result =createFlower(resultSet);}
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.belykh.finalProj.dao.FlowerDAO#findFlowerByName(java.lang.String)
     */
    @Override
    public boolean findFlowerByName(String name) throws DAOException {

        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_FLOWER_BY_NAME)) {
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /* (non-Javadoc)
     * @see com.belykh.finalProj.dao.FlowerDAO#findAllFlowers()
     */
    @Override
    public List<FlowerDBO> findAllFlowers() throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_FLOWERS);
            return createFlowersList(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /* (non-Javadoc)
     * @see com.belykh.finalProj.dao.FlowerDAO#addFlower(com.belykh.finalProj.entity.dbo.FlowerDBO)
     */
    @Override
    public boolean addFlower(FlowerDBO flowerDBO) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_FLOWER)) {
            setStatement(statement, flowerDBO);
            return (statement.executeUpdate()!=0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private List<FlowerDBO> createFlowersList(ResultSet resultSet) throws SQLException {
        List<FlowerDBO> resultList = new ArrayList<>();
        while(resultSet.next()){
            resultList.add(createFlower(resultSet));
        }
        return resultList;
    }

    private FlowerDBO createFlower(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(FLOWER_ID);
        String name = resultSet.getString(FLOWER_NAME);
        return new FlowerDBO(id,name);
    }
    private void setStatement(PreparedStatement ps, FlowerDBO flowerDBO) throws SQLException, DAOException {
        ps.setString(1, flowerDBO.getName());
    }
}
