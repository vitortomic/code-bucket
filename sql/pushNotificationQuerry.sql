SELECT 
    GROUP_CONCAT(DISTINCT ad.city
        SEPARATOR ',') AS `job cities`,
    u.id AS 'user id',
    u.firstName,
    u.lastName,
    u.email,
    GROUP_CONCAT(DISTINCT ios.token
        SEPARATOR ',') AS `ios tokens`,
    GROUP_CONCAT(DISTINCT android.channel
        SEPARATOR ',') AS `android channels`
FROM
    JobRequestDay jrd
        JOIN
    JobRequest jr ON jrd.jobRequest_id = jr.id
        JOIN
    Address ad ON jr.jobAddress_id = ad.id
        JOIN
    JobMatchDay jmd ON jmd.jobrequestday_id = jrd.id
        JOIN
    JobMatch jm ON jmd.jobMatch_id = jm.id
        JOIN
    User u ON jm.personnel_id = u.id
        LEFT JOIN
    UserIosTokenActiveView ios ON ios.user_id = u.id
        LEFT JOIN
    UserAndroidChannelActiveView android ON android.user_id = u.id
WHERE
    jrd.status = 'ACTIVE'
        AND jmd.status = 'PENDING'
        AND jm.fullMatch = 1
        AND (jr.dateInitiated BETWEEN NOW() - INTERVAL 1 DAY AND NOW())
GROUP BY u.id