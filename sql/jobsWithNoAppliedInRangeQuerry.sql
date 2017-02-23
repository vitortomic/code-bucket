SELECT 
    jrd.referenceID,
    lt.name,
    ad.city,
    ad.state,
    jrd.date,
    jrd.startTime,
    jrd.endTime,
    u.id AS 'user id',
    u.firstName,
    u.lastName,
    u.email
FROM
    JobRequestDay jrd
        JOIN
    JobRequest jr ON jrd.jobRequest_id = jr.id
        JOIN
    LicenceType lt ON jr.licenceType_id = lt.id
        JOIN
    Address ad ON jr.jobAddress_id = ad.id
        JOIN
    JobMatchDay jmd ON jmd.jobrequestday_id = jrd.id
        JOIN
    JobMatch jm ON jmd.jobMatch_id = jm.id
        JOIN
    User u ON jm.personnel_id = u.id
WHERE
    jrd.status = 'ACTIVE'
        AND (SELECT 
            COUNT(jm2.id)
        FROM
            JobRequest jr2
                JOIN
            JobMatch jm2 ON jm2.jobRequest_id = jr2.id
        WHERE
            jm2.desiredRate <= jr2.maxRange
                AND jr2.id = jr.id) = 0
                
        AND (jr.dateInitiated BETWEEN NOW() - INTERVAL 1 DAY AND NOW()
        OR (jr.dateInitiated < NOW() - INTERVAL 78 HOUR
        AND jrd.date BETWEEN NOW() + INTERVAL 24 HOUR AND NOW() + INTERVAL 48 HOUR))