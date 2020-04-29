package ua.nure.borisenko.practice8.db;

import ua.nure.borisenko.practice8.db.entity.Team;
import ua.nure.borisenko.practice8.db.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DBManager {

    private static DBManager instance;
    private static final Lock CONNECTION_LOCK = new ReentrantLock();
    private Connection connection;

    private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT ,?)";
    private static final String SQL_INSERT_TEAM = "INSERT INTO teams VALUES (DEFAULT,?)";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_FIND_ALL_TEAMS = "SELECT * FROM teams";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SQL_FIND_TEAM_BY_NAME = "SELECT * FROM teams WHERE name=?";
    private static final String SQL_INSERT_USER_TO_TEAM = "INSERT INTO users_teams VALUES(?,?)";
    private static final String SQL_FIND_TEAM_BY_USER_ID = "SELECT t.id, t.name FROM users_teams ut\n"
            + "JOIN users u ON ut.user_id = u.id\n"
            + "JOIN teams t ON ut.team_id = t.id\n"
            + "WHERE u.id = ?";
    private static final String SQL_DELETE_TEAM = "DELETE FROM teams WHERE name=?";
    private static final String SQL_UPDATE_TEAM = "UPDATE teams SET name=? WHERE id=?";


    private DBManager() throws IOException, SQLException {
        Properties properties = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream("app.properties");
            properties.load(fis);
            String connectionUrl = properties.getProperty("connection.url");
            connection = DriverManager.getConnection(connectionUrl);
        } catch (IOException e) {
            System.err.println("Priorities don't exist");
        }
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            try {
                instance = new DBManager();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public boolean insertUser(User user) {
        PreparedStatement ps = null;
        ResultSet id = null;

        try {
            CONNECTION_LOCK.lock();
            ps = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            if (ps.executeUpdate() != 1) {
                return false;
            }
            id = ps.getGeneratedKeys();
            if (id.next()) {
                int idField = id.getInt(1);
                user.setId(idField);
            }
        } catch (SQLException e) {
            System.out.println("Can't insert user:" + e.getMessage());
            return false;
        } finally {
            close(id);
            close(ps);
            CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public List<User> findAllUsers() {
        Statement st = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            CONNECTION_LOCK.lock();
            st = connection.createStatement();
            rs = st.executeQuery(SQL_FIND_ALL_USERS);
            while (rs.next()) {
                User user = new User();
                users.add(user);
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Can't find all users:" + e.getMessage());
            return Collections.emptyList();
        } finally {
            close(st);
            close(rs);
            CONNECTION_LOCK.unlock();
        }
        return users;
    }

    public boolean insertTeam(Team team) {
        PreparedStatement ps = null;
        ResultSet id = null;
        try {
            CONNECTION_LOCK.lock();
            ps = connection.prepareStatement(SQL_INSERT_TEAM, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, team.getName());
            if (ps.executeUpdate() != 1) {
                return false;
            }
            id = ps.getGeneratedKeys();
            if (id.next()) {
                int idField = id.getInt(1);
                team.setId(idField);
            }
        } catch (SQLException e) {
            System.out.println("Can't insert team:" + e.getMessage());
            return false;
        } finally {
            close(id);
            close(ps);
            CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public List<Team> findAllTeams() {
        Statement st = null;
        ResultSet rs = null;
        List<Team> teams = new ArrayList<>();
        try {
            CONNECTION_LOCK.lock();
            st = connection.createStatement();
            rs = st.executeQuery(SQL_FIND_ALL_TEAMS);
            while (rs.next()) {
                Team team = new Team();
                teams.add(team);
                team.setId(rs.getInt(1));
                team.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Can't find all team" + e.getMessage());
            return Collections.emptyList();
        } finally {
            close(st);
            close(rs);
            CONNECTION_LOCK.unlock();
        }
        return teams;
    }

    public User getUser(String login) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            CONNECTION_LOCK.lock();
            ps = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Can't get user");
        } finally {
            close(rs);
            close(ps);
            CONNECTION_LOCK.unlock();

        }
        return user;
    }

    public Team getTeam(String name) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Team team = null;
        try {
            CONNECTION_LOCK.lock();
            ps = connection.prepareStatement(SQL_FIND_TEAM_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                team = new Team();
                team.setId(rs.getInt(1));
                team.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Can't find team");
        } finally {
            close(rs);
            close(ps);
            CONNECTION_LOCK.unlock();
        }
        return team;
    }

    public boolean setTeamsForUser(User user, Team... teams) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            CONNECTION_LOCK.lock();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(SQL_INSERT_USER_TO_TEAM);
            for (Team t : teams) {
                ps.setInt(1, user.getId());
                ps.setInt(2, t.getId());
                ps.addBatch();
            }
            int[] usersTeams = ps.executeBatch();
            for (int i : usersTeams) {
                if (i != 1) {
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Can't set groups");
        } finally {
            close(rs);
            close(ps);
            setAutocommit();
            CONNECTION_LOCK.unlock();
        }
        return false;
    }

    public List<Team> getUserTeams(User user) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Team> teams = new ArrayList<>();
        try {
            CONNECTION_LOCK.lock();
            ps = connection.prepareStatement(SQL_FIND_TEAM_BY_USER_ID);
            ps.setInt(1, user.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Team team = new Team();
                teams.add(team);
                team.setId(rs.getInt(1));
                team.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Can't get teams:" + e.getMessage());
            return Collections.emptyList();
        } finally {
            close(rs);
            close(ps);
            CONNECTION_LOCK.unlock();
        }
        return teams;
    }

    public boolean deleteTeam(Team team) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            CONNECTION_LOCK.lock();
            ps = connection.prepareStatement(SQL_DELETE_TEAM);
            ps.setString(1, team.getName());
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Can't delete group:" + e.getMessage());
            return false;
        } finally {
            close(rs);
            close(ps);
            CONNECTION_LOCK.unlock();
        }
        return true;
    }

    public boolean updateTeam(Team team) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            CONNECTION_LOCK.lock();
            ps = connection.prepareStatement(SQL_UPDATE_TEAM);
            ps.setString(1, team.getName());
            ps.setInt(2, team.getId());
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Can't update team:" + e.getMessage());
            return false;
        } finally {
            close(rs);
            close(ps);
            CONNECTION_LOCK.unlock();
        }
        return true;
    }

    private static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setAutocommit() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
