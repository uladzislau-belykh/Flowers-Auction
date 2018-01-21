package com.belykh.finalProj.dao.impl;

import com.belykh.finalProj.dao.CityDAO;
import com.belykh.finalProj.entity.CityDBO;
import com.belykh.finalProj.exception.DAOException;
import com.belykh.finalProj.pool.ConnectionPool;
import com.belykh.finalProj.pool.exception.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panda on 14.1.18.
 */
public class CityDAOImpl implements CityDAO {

    private static final String SQL_FIND_CITY_BY_ID="SELECT `city`.`id`,`city`.`city_name`,`city`.`country_id_fk` FROM `city` WHERE `city`.`id`=?";
    private static final String SQL_FIND_ALL_CITIES="SELECT `city`.`id`,`city`.`city_name`,`city`.`country_id_fk` FROM `city` ";
    private static final String SQL_FIND_CITIES_BY_COUNTRY_ID="SELECT `city`.`id`,`city`.`city_name`,`city`.`country_id_fk` FROM `city` WHERE `country_id_fk`=?";
    private static final String SQL_ADD_CITY = "INSERT INTO `city` SET `city_name`=?,`country_id_fk`=?";
    private static final String SQL_DELETE_CITY = "DELETE FROM `city` WHERE `city`.`id`=?";


    private static final String CITY_ID = "id";
    private static final String CITY_NAME=  "city_name";
    private static final String CITY_COUNTRY_ID = "country_id_fk";

    @Override
    public CityDBO findCityById(Long id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_CITY_BY_ID)) {
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            return createCity(resultSet);
        } catch (SQLException |ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<CityDBO> findAllCities() throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_CITIES);
            return createCityList(resultSet);
        } catch (SQLException |ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<CityDBO> findCitiesByCountryId(Long countryId) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_CITIES_BY_COUNTRY_ID)) {
            statement.setLong(1,countryId);
            ResultSet resultSet = statement.executeQuery();
            return createCityList(resultSet);
        } catch (SQLException |ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean addCity(CityDBO city) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_CITY)) {
            setStatement(statement, city);
            return (statement.executeUpdate()!=0);
        } catch (SQLException|ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CITY)) {
            statement.setLong(1,id);
            return (statement.executeUpdate()!=0);
        } catch (SQLException|ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }


    private List<CityDBO> createCityList(ResultSet resultSet) throws SQLException {
        List<CityDBO> resultList = new ArrayList<>();
        while(resultSet.next()){
            resultList.add(createCity(resultSet));
        }
        return resultList;
    }

    private CityDBO createCity(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(CITY_ID);
        String name = resultSet.getString(CITY_NAME);
        Long countryId = resultSet.getLong(CITY_COUNTRY_ID);
        return new CityDBO(id,name,countryId);
    }
    private void setStatement(PreparedStatement ps, CityDBO city) throws SQLException, DAOException {
        ps.setString(1, city.getName());
        ps.setLong(2,city.getCountryId());
    }
}
