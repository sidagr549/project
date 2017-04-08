__author__ = 'chetan dashora'


from django.conf.urls import url
from donation import views


urlpatterns = [
    url(r'^register/',views.register,name='register'),
    url(r'^group_register/',views.group_register,name='gregister'),
    url(r'^accounts/login/',views.user_login,name="login"),
    url(r'^accounts/logout/',views.user_logout,name="logout"),
    url(r'^find_group/',views.find_group,name="fgroup"),
    url(r'^try_group/',views.try_group,name="fgroup"),
    url(r'^submit_amount/',views.submit_amount,name="samount"),
    url(r'^check_previous_status/',views.check_previous_status,name='checkstatus'),


]
