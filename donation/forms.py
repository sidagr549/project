__author__ = 'chetan dashora'
from django import forms

from .models import UserProfile,Group,User


class UserForm(forms.ModelForm):
    password = forms.CharField(widget=forms.PasswordInput)

    class Meta():
        model = User
        fields = ('username', 'password', 'email')




class GroupForm(forms.ModelForm):

    class Meta():
        model = Group
        fields = ('name','amount_required','min_amount_req','description','type','max_deposit_per_person')