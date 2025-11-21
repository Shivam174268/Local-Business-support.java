package com.localbiz.service;

import com.localbiz.model.Review;
import com.localbiz.db.Database;
import com.localbiz.util.IDGenerator;

import java.sql.*;
import java.util.*;

/**
 * Service class to manage Reviews.
 */
public class ReviewService implements ServiceInterface {
    private final Map<String, Review> reviewCache = Collections.synchronizedMap(new HashMap<>());
    private final Database db = Database.getInstance();

    public Review addReview(Review review) throws SQLException {
        if (review.getRating() < 1 || review.getRating() > 5)
            throw new IllegalArgumentException("Rating must be 1 to 5");

        if (review.getId() == null)
            review = new Review(IDGenerator.generate(), review.getUserId(), review.getBusinessId(), review.getRating(), review.getComment());

        String sql = "INSERT INTO reviews(id,user_id,business_id,rating,comment) VALUES(?,?,?,?,?)";
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, review.getId());
            ps.setString(2, review.getUserId());
            ps.setString(3, review.getBusinessId());
            ps.setInt(4, review.getRating());
            ps.setString(5, review.getComment());
            ps.executeUpdate();
            reviewCache.put(review.getId(), review);
        }
        return review;
    }

    public List<Review> getReviewsByBusinessId(String businessId) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE business_id=?";
        try (Connection c = db.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, businessId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Review r = new Review(rs.getString("id"), rs.getString("user_id"),
                            rs.getString("business_id"), rs.getInt("rating"), rs.getString("comment"));
                    reviews.add(r);
                    reviewCache.put(r.getId(), r);
                }
            }
        }
        return reviews;
    }
}
