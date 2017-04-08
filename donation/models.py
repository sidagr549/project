from django.db import models
from django.template.defaultfilters import slugify
from django.contrib.auth.models import User
import uuid


class UserProfile(models.Model):
    user = models.OneToOneField(User,related_name="profile")
    projects_involved = models.TextField()
    groups = models.TextField()
    check_group=models.CharField(max_length=25,blank=True,null=True)


    def __str__(self):
        return self.user.username


class Group(models.Model):
    unique_id = models.CharField(max_length=25)
    description = models.CharField(max_length=1000,null=True,blank=True)
    user=models.ManyToManyField(UserProfile)
    initiator = models.CharField(max_length=50)
    name = models.CharField(max_length=50,unique=False)
    type = models.CharField(max_length=50)
    members = models.IntegerField(blank=True,null=True)
    amount_required=models.DecimalField(decimal_places=2,max_digits=18,null=True,blank=True)
    min_amount_req = models.DecimalField(decimal_places=2,max_digits=18)
    max_deposit_per_person=models.DecimalField(decimal_places=2,max_digits=18,default=100.00)
    contri_col = models.DecimalField(decimal_places=2,max_digits=10,default=0)
    start_date = models.DateField()

    # deadline = models.DateField(blank=True,null=True)
    # slug=models.SlugField(unique=True,default=uuid.uuid4)
    #
    # def save(self,*args, **kwargs):
    #     self.slug=slugify(self.name)
    #     super(Group,self).save(*args, **kwargs)

    class Meta:
        verbose_name_plural='Groups'

    def __str__(self):
        return self.name

class Deposit(models.Model):
    user=models.ForeignKey(UserProfile,related_name="userdeposit")
    group=models.ForeignKey(Group,related_name="groupdeposit")
    amount=models.DecimalField(max_digits=10,decimal_places=2)


