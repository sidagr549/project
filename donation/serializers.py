__author__ = 'chetan dashora'
from rest_framework import serializers

from .models import UserProfile,Group,User


class UserSerializer(serializers.ModelSerializer):
    # password = serializers.CharField(widget=serializers.PasswordInput)
    userprofile = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    class Meta():
        model = User
        fields = ('username', 'email', 'password')


class UserProfileSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserProfile
        fields = ('projects_involved',)


class GroupSerializer(serializers.ModelSerializer):

    class Meta():
        model = Group
        fields = ('name','type','contri_req','deadline')