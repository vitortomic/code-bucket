SELECT distinct u.id as 'ID', u.practiceName as 'Office name',
(	select (concat(concat(a.addressLine,','),a.city))
	from Address a
	where id=u.id
) as 'Address',
(	select a.state
	from address a
	where id=u.id
) as 'State',
(	select count(id)+1 
	from ClientOffice 
    where user_id=u.id
) as 'Number of locations',
u.dateRegistered as 'Date of registration',
(	SELECT count(id) 
	FROM JobRequest 
    where month(firstDayDate)=month(u.dateRegistered) and year(firstDayDate)=year(dateRegistered) and client_id=u.id
) as 'Job posts in first month',
(	SELECT count(jm.id)
	FROM JobMatch jm join JobMatchStatusLog jmsl on jmsl.jobMatch_id=jm.id join JobRequest jr on jr.id=jm.jobRequest_id 
    where jmsl.status='ASSIGNED' and jr.client_id=u.id and month(firstDayDate)=month(u.dateRegistered) and year(firstDayDate)=year(dateRegistered)
) as 'Job placements in first month',
(	SELECT count(id) 
	FROM JobRequest 
    where month(firstDayDate)=month(date_add(dateRegistered,interval 1 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 1 month)) and client_id=u.id
) as 'Job posts in second month',
(	SELECT count(jm.id)
	FROM JobMatch jm join JobMatchStatusLog jmsl on jmsl.jobMatch_id=jm.id join JobRequest jr on jr.id=jm.jobRequest_id 
    where jmsl.status='ASSIGNED' and jr.client_id=u.id and month(firstDayDate)=month(date_add(dateRegistered,interval 1 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 1 month))
) as 'Job placements in second month',
(	SELECT count(id) 
	FROM JobRequest 
    where month(firstDayDate)=month(date_add(dateRegistered,interval 2 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 2 month)) and client_id=u.id
) as 'Job posts in third month',
(	SELECT count(jm.id)
	FROM JobMatch jm join JobMatchStatusLog jmsl on jmsl.jobMatch_id=jm.id join JobRequest jr on jr.id=jm.jobRequest_id 
    where jmsl.status='ASSIGNED' and jr.client_id=u.id and month(firstDayDate)=month(date_add(dateRegistered,interval 2 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 2 month))
) as 'Job placements in third month',
(	SELECT count(id) 
	FROM JobRequest 
    where month(firstDayDate)=month(date_add(dateRegistered,interval 3 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 3 month)) and client_id=u.id
) as 'Job posts in fourth month',
(	SELECT count(jm.id)
	FROM JobMatch jm join JobMatchStatusLog jmsl on jmsl.jobMatch_id=jm.id join JobRequest jr on jr.id=jm.jobRequest_id 
    where jmsl.status='ASSIGNED' and jr.client_id=u.id and month(firstDayDate)=month(date_add(dateRegistered,interval 3 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 3 month))
) as 'Job placements in fourth month',
(	SELECT count(id) 
	FROM JobRequest 
    where month(firstDayDate)=month(date_add(dateRegistered,interval 4 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 4 month)) and client_id=u.id
) as 'Job posts in fifth month',
(	SELECT count(jm.id)
	FROM JobMatch jm join JobMatchStatusLog jmsl on jmsl.jobMatch_id=jm.id join JobRequest jr on jr.id=jm.jobRequest_id 
    where jmsl.status='ASSIGNED' and jr.client_id=u.id and month(firstDayDate)=month(date_add(dateRegistered,interval 4 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 4 month))
) as 'Job placements in fifth month',
(	SELECT count(id) 
	FROM JobRequest 
    where month(firstDayDate)=month(date_add(dateRegistered,interval 5 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 5 month)) and client_id=u.id
) as 'Job posts in sixth month',
(	SELECT count(jm.id)
	FROM JobMatch jm join JobMatchStatusLog jmsl on jmsl.jobMatch_id=jm.id join JobRequest jr on jr.id=jm.jobRequest_id 
    where jmsl.status='ASSIGNED' and jr.client_id=u.id and month(firstDayDate)=month(date_add(dateRegistered,interval 5 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 5 month))
) as 'Job placements in sixth month',
(	SELECT count(id) 
	FROM JobRequest 
    where month(firstDayDate)=month(date_add(dateRegistered,interval 6 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 6 month)) and client_id=u.id
) as 'Job posts in seventh month',
(	SELECT count(jm.id)
	FROM JobMatch jm join JobMatchStatusLog jmsl on jmsl.jobMatch_id=jm.id join JobRequest jr on jr.id=jm.jobRequest_id 
    where jmsl.status='ASSIGNED' and jr.client_id=u.id and month(firstDayDate)=month(date_add(dateRegistered,interval 6 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 6 month))
) as 'Job placements in seventh month',
(	SELECT count(id) 
	FROM JobRequest 
    where month(firstDayDate)=month(date_add(dateRegistered,interval 7 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 7 month)) and client_id=u.id
) as 'Job posts in eight month',
(	SELECT count(jm.id)
	FROM JobMatch jm join JobMatchStatusLog jmsl on jmsl.jobMatch_id=jm.id join JobRequest jr on jr.id=jm.jobRequest_id 
    where jmsl.status='ASSIGNED' and jr.client_id=u.id and month(firstDayDate)=month(date_add(dateRegistered,interval 7 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 7 month))
) as 'Job placements in eight month',
(	SELECT count(id) 
	FROM JobRequest 
    where month(firstDayDate)=month(date_add(dateRegistered,interval 8 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 8 month)) and client_id=u.id
) as 'Job posts in ninth month',
(	SELECT count(jm.id)
	FROM JobMatch jm join JobMatchStatusLog jmsl on jmsl.jobMatch_id=jm.id join JobRequest jr on jr.id=jm.jobRequest_id 
    where jmsl.status='ASSIGNED' and jr.client_id=u.id and month(firstDayDate)=month(date_add(dateRegistered,interval 8 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 8 month))
) as 'Job placements in ninth month',
(	SELECT count(id) 
	FROM JobRequest 
    where month(firstDayDate)=month(date_add(dateRegistered,interval 9 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 9 month)) and client_id=u.id
) as 'Job posts in tenth month',
(	SELECT count(jm.id)
	FROM JobMatch jm join JobMatchStatusLog jmsl on jmsl.jobMatch_id=jm.id join JobRequest jr on jr.id=jm.jobRequest_id 
    where jmsl.status='ASSIGNED' and jr.client_id=u.id and month(firstDayDate)=month(date_add(dateRegistered,interval 9 month)) and year(firstDayDate)=year(date_add(dateRegistered,interval 9 month))
) as 'Job placements in tenth month'


FROM user u left join jobrequest jr on u.id=jr.client_id
WHERE u.type='CLIENT'