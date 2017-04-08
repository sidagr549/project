from django.shortcuts import render
from .serializers import UserProfileSerializer,UserSerializer
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
from django.contrib.auth import authenticate, login , logout
from rest_framework.renderers import JSONRenderer
from django.http import HttpResponse
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.parsers import JSONParser
from django.contrib.auth.decorators import login_required
from .models import UserProfile,Group,Deposit,User
from .forms import UserForm,GroupForm
import time,datetime

usel=";"



@csrf_exempt
def register(request):
    if request.method == "POST":
        user_serializer = UserForm(request.POST)
        if user_serializer.is_valid():
            user=user_serializer.save(commit=False)

            user.set_password(user.password)
            user.save()
            userprofile=UserProfile.objects.create(user=user,projects_involved="",groups="")
            userprofile.save()
            print("hi")
            return HttpResponse("true")
        else:
            print(user_serializer.errors)
            return HttpResponse("false")
    print ("hfjjfk")
    return HttpResponse("false")


@csrf_exempt
def user_login(request):
    #status=False
    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']
        user = authenticate(username=username, password=password)
        if user:
            if user.is_active:
                login(request, user)
                status="true"
                return HttpResponse(str(status))
            else:
                return HttpResponse("Your account is disabled.")
        else:
            print ("Invalid login details: {0}, {1}".format(username, password))
            return HttpResponse("false")
    return HttpResponse("false")

@csrf_exempt
def group_register(request):
    if request.method == "POST":
        # a=request.POST
        # a["unique_id"]=str(time.time())

        groupform=GroupForm(request.POST)

        if groupform.is_valid():
            group=groupform.save(commit=False)
            group.unique_id=str(int(time.time()))
            # group.user=request.user
            us=request.POST["user"]
            user=User.objects.get(username=us)
            group.initiator=user.username
            group.start_date=datetime.date.today()
            group.save()
            user.profile.groups+=str(group.name+";")
            user.save()
            group.user.add(user.profile)
            group.save()

            print("hi")
            return HttpResponse(group.unique_id)
        else:
            print(groupform.errors)
            return HttpResponse("false")
    print ("hfjjfk")
    return HttpResponse("false")

#
# def add_beneficiaries(request):
#     for keys in request.POST.keys():
#
#
# def approved(request):
#     yaha tu kya bejega
#
fal=0
@csrf_exempt
def try_group(request):
    global usel
    if(request.method == "POST"):

        res={}
        code=request.POST["Code"]
        print(code)
        us=request.POST["user"]
        user=User.objects.get(username=us)
        user.profile.check_group=code
        user.profile.save()
        usel=user
        try:
            group = Group.objects.prefetch_related('user').get(unique_id=code)
            return HttpResponse('true')
        except Group.DoesNotExist:
            return HttpResponse("false")
    return HttpResponse("false")

@csrf_exempt
def find_group(request):
    global usel
    print(request.POST.keys())
    if(request.method == "POST" and usel !=";"):


        res={}
        user=User.objects.get(username=usel).profile
        code=user.check_group
        try:
            group = Group.objects.prefetch_related('user').get(unique_id=code)
            res["name"]=group.name
            res["description"]=group.description
            res["amount_remaining"]=group.min_amount_req-group.contri_col
            res["amount_collected"]=group.contri_col
            res["max_limit_per_person"]=group.max_deposit_per_person


            u=user.user
            if u in group.user.all():
                try:
                    res["contribution"]=Deposit.objects.prefetch_related('user','group').filter(group=group).get(user=u).amount
                except Deposit.DoesNotExist:
                    return JsonResponse(res)
            return JsonResponse(res)
        except Group.DoesNotExist:
            return JsonResponse({"status":"false"})
    fal=1
    return JsonResponse({"status":"false"})


@csrf_exempt
def submit_amount(request):
    if request.method == "POST" :
        code=request.POST["Code"]
        us=request.POST["user"]
        user=User.objects.get(username=us).profile
        amt=request.POST["amount"]
        group = Group.objects.prefetch_related('user').get(unique_id=code)
        if(user in group.user.all()):
            if(user.user.username != group.initiator):
                return HttpResponse("cannot submit multiple")
            else:
                group.initiator=group.initiator+ "paid"
        deposit=Deposit(user=user,group=group,amount=amt)
        group.contri_col = float(group.contri_col)+float(amt)
        if(group.contri_col>group.min_amount_req):
            return HttpResponse("Please submit only"+str(group.min_amount_req-group.contri_col)+"Rupees")
        us=request.POST["user"]
        user=User.objects.get(username=us)
        group.user.add(user.profile)
        group.save()
        deposit.save()
        return HttpResponse("Success")
    return HttpResponse("Failure")

@csrf_exempt
def check_previous_status(request):
    strr=";"
    if request.method=="GET" :
        us=request.GET["user"]
        user=User.objects.get(username=us)
        for d in user.profile.userdeposit.all():
            strr=""
            break
        for d in user.profile.userdeposit.all():
            strr=strr+str(d.group.name)+":"+str(d.group.unique_id)+";"
        if(strr==";"):
            return HttpResponse("false")
        return HttpResponse(strr)


@login_required
def user_logout(request):
    logout(request)

# Create your views here.


from django.conf import settings
from django.db.models.signals import post_save
from django.dispatch import receiver
from rest_framework.authtoken.models import Token

@receiver(post_save, sender=settings.AUTH_USER_MODEL)
def create_auth_token(sender, instance=None, created=False, **kwargs):
    if created:
        Token.objects.create(user=instance)



@csrf_exempt
def try_group(request):
    global usel
    if(request.method == "POST"):

        res={}
        code=request.POST["Code"]
        print(code)
        us=request.POST["user"]
        user=User.objects.get(username=us)
        user.profile.check_group=code
        user.profile.save()
        usel=user
        try:
            group = Group.objects.prefetch_related('user').get(unique_id=code)
            return HttpResponse('true')
        except Group.DoesNotExist:
            return HttpResponse("false")
    return HttpResponse("false")
