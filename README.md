# DecisionTreeImpl
## In this class We use DTL algorithm for building the decision tree.
## Helper function:
### private double H(double[] p)-
#### This function help us for entropy it get array of probabilities and return their entrop (H)
### private void p(double[] p0, List<Instance> instances)-
#### This function help us find the probabilities when we calculate H(class).
#### It takes p0 and update it to the  probabilities it.
#### The form of the work: For all labels it counter how many intances have the label, 
####                       after it devide the counters by the number of instances, for getting the probabilities.
### private double informationGain(List<String> labels, List<String> attributes, List<Instance> instances, int i, double hClass)-
#### This function get the value of H(class) and th index i for the attribute attributes(i), and calculate H(class|attribute(i)),
#### and return informaion gain = H(class) -  H(class|attribute(i)).
#### The form of the work: p1- cut the probabilities of p(vj|attribut(i)), that is in p1[j] will be the probability that attribute(i)=vj in instances.
####                       p2- p2[j]={p(labels(k)| vj)}, that is in p2[j][k] the probabily that the label will be labels[k] if attribute(i)=vj .
####                       hOnConditionAttribute we sum for all j p1[j] * H(p2[j])
### private String chooseAttribute(List<String> attributes, List<Instance> instances) - 
#### This function calculate the maximal Information gain for all attributes.
### private boolean isTheSameClassification(List<Instance> instances) -
#### This function checks if all instances have the same result (label).
### private void updateExamples(List<Instance> examples, List<Instance> instances, List<String> attributes, String best,String vi)-
#### This function pass all instances and add to examples just who have best=vi
#### but with out the attribute best.
### private DecTreeNode DTL(List<Instance> instances, List<String> attributes, String fatheValue)-
#### This function build decision tree by using the algorithem DTL
### private String getClassify(DecTreeNode root, Instance instance) -
#### This function predict the result(label) of the instance.
#### This is recursive function that look for the answer in the decision tree.


## Function:
### public void rootInfoGain(DataSet train) -
#### This function prints the information gain (one in each line) for all the
#### attributes at the root based on the training set, train. 
### public String classify(Instance instance) -
#### This function predict the result(label) of the instance,
#### by using recursive function getClassify.
### public void printAccuracy(DataSet test) -
#### This function print the seccess rates precent od our decision tree.
#### it will checks how many times the decision tree predict the correct result (label).


## way of running: we run HW3
### examples  if runing
#### * args[0] = 0 args[1]= "...\examples1.txt" args[2]= "...\examples1.txt"
![image](https://github.com/talStamker/readmee/assets/89009470/fbcf0924-5136-4397-bb6c-da20fea6e90c)
#### * args[0] = 1 args[1]= "...\examples1.txt" args[2]= "...\examples1.txt"
![image](https://github.com/talStamker/readmee/assets/89009470/cee6da07-c4b4-48d3-a7df-5a85586f54d6)

![image](https://github.com/talStamker/readmee/assets/89009470/00f95153-c6dd-4677-9e39-ed69e6cb2b20)

![image](https://github.com/talStamker/readmee/assets/89009470/8231a667-211d-4137-a3b5-27be52bccedb)

![image](https://github.com/talStamker/readmee/assets/89009470/b0b4b8b1-aedc-4dd9-93ca-2d50f7822241)

![image](https://github.com/talStamker/readmee/assets/89009470/5ce3e353-0f32-4386-a86a-0d681f9f4344)

![image](https://github.com/talStamker/readmee/assets/89009470/0d2351a6-c06f-4acc-a042-ce5f5a6e0dd1)

![image](https://github.com/talStamker/readmee/assets/89009470/e8c9412d-919a-44cf-8059-8bcbdb7d2086)

![image](https://github.com/talStamker/readmee/assets/89009470/6a731377-814a-4cfd-9fd3-dfae7da00e71)

![image](https://github.com/talStamker/readmee/assets/89009470/c3c68425-b40b-4d74-afe1-9068e49fac2c)

![image](https://github.com/talStamker/readmee/assets/89009470/a4a0846d-5196-46ac-bbeb-c614e5bbec2e)

![image](https://github.com/talStamker/readmee/assets/89009470/5f9eb124-7219-4e05-aaf1-e53910fe0d27)

![image](https://github.com/talStamker/readmee/assets/89009470/04eda7e8-d29a-450b-8d15-a43defccd8dd)

![image](https://github.com/talStamker/readmee/assets/89009470/0a14d136-6996-4a14-b6b8-a0b13ff0048c)


####  * args[0] = 2 args[1]= "...\examples1.txt" args[2]= "...\examples1.txt"

![image](https://github.com/talStamker/readmee/assets/89009470/f73db62d-9b6d-4baa-846d-31144ad32026)
